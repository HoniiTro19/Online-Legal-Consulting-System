import thulac
import joblib
import gzip
import socket
import threading
import json
import numpy as np
import ipdb
import os

tfidf = joblib.load('./model/tfidf.model')
law = joblib.load('./model/law.model')
accu = joblib.load('./model/accu.model')
time = joblib.load('./model/time.model')

batch_size = 1
cut = thulac.thulac(seg_only = True)

data_path = "law"
for file_name in os.listdir(data_path):
    inf = open(os.path.join(data_path, file_name), "r", encoding='utf-8')
    id2lawid = {}
    for line in inf:
        id2lawid[len(id2lawid)+1] = int(line.strip('\n'))

def predict_law(vec):
    y = [law.predict(vec)[0]]
    y = [id2lawid[temp+1] for temp in y]
    return y

def predict_accu(vec):
    y = accu.predict(vec)
    return [y[0]+1]

def predict_time(vec):
    y = time.predict(vec)[0]

    #返回每一个罪名区间的中位数
    if y == 0:
        return -2
    if y == 1:
        return -1
    if y == 2:
        return 120
    if y == 3:
        return 102
    if y == 4:
        return 72
    if y == 5:
        return 48
    if y == 6:
        return 30
    if y == 7:
        return 18
    else:
        return 6

def predict(content):
    fact = cut.cut(content, text = True)

    vec = tfidf.transform([fact])
    ans = {}

    ans['accusation'] = np.array(predict_accu(vec)).tolist()
    ans['articles'] = np.array(predict_law(vec)).tolist()
    ans['imprisonment'] = predict_time(vec)

    return ans



def main():
    # 创建服务器套接字
    serversocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    # 获取本地主机名称
    host = socket.gethostname()
    # 设置一个端口
    port = 12345
    # 将套接字与本地主机和端口绑定
    serversocket.bind((host,port))
    # 设置监听最大连接数
    serversocket.listen(5)
    # 获取本地服务器的连接信息
    myaddr = serversocket.getsockname()
    print("服务器地址:%s"%str(myaddr))
    # 循环等待接受客户端信息
    while True:
        # 获取一个客户端连接
        clientsocket,addr = serversocket.accept()
        print("连接地址:%s" % str(addr))
        try:
            t = ServerThreading(clientsocket)#为每一个请求开启一个处理线程
            t.start()
            pass
        except Exception as identifier:
            print(identifier)
            pass
        pass
    serversocket.close()
    pass



class ServerThreading(threading.Thread):
    def __init__(self,clientsocket,recvsize=1024*1024,encoding="gbk"):
        threading.Thread.__init__(self)
        self._socket = clientsocket
        self._recvsize = recvsize
        self._encoding = encoding
        pass

    def run(self):
        print("开启线程.....")
        try:
            #接受数据
            msg = ''
            while True:
                # 读取recvsize个字节
                rec = self._socket.recv(self._recvsize)
                # 解码
                msg += rec.decode(self._encoding)
                # 文本接受是否完毕，因为python socket不能自己判断接收数据是否完毕，
                # 所以需要自定义协议标志数据接受完毕
                if msg.strip().endswith('over'):
                    msg=msg[:-4]
                    break
            # 解析json格式的数据
            re = json.loads(msg)
            # 调用神经网络模型处理请求
            res = predict(re['content'])
            sendmsg = json.dumps(res)
            # 发送数据
            self._socket.send(("%s"%sendmsg).encode(self._encoding))
            pass
        except Exception as identifier:
            self._socket.send("500".encode(self._encoding))
            print(identifier)
            pass
        finally:
            self._socket.close()
        print("任务结束.....")

        pass

    def __del__(self):

        pass
if __name__ == "__main__":
    main()
