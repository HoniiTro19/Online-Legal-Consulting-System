package com.huidong.legalsys.service;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.huidong.legalsys.dao.AccusationDao;
import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 用户咨询的业务逻辑层
 */
@Service
public class ConsultService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Value("${config.pattern}")
    String pattern;
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private ConvrDao convrDao;
    @Autowired
    private AccusationDao accusationDao;

    /**
     * @param id 咨询编号
     * @return Consult 咨询信息
     * @Description 获得咨询信息
     */
    public Consult getConsult(Integer id) {
        Consult consult = consultDao.getConsultInfo(id);
        return consult;
    }

    /**
     * @param id 会话编号
     * @return Consult 会话信息
     * @Description 获得会话信息
     */
    public Convr getConvr(Integer id) {
        Convr convr = convrDao.getConvrInfo(id);
        return convr;
    }

    /**
     * @param phone 手机号
     * @param content 咨询内容
     * @return String 智能预测详情
     * @throws IOException
     * @Description 新建用户咨询
     */
    public String newconsult(String phone, String title, String content) {

        String result = remoteCall(content);
        if (result == null) {
            throw new LegalsysException(ErrorEnum.CONSULT_ERROR);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        String time = format.format(date);
        Consult consult = new Consult();
        consult.setPhone(phone);
        consult.setTitle(title);
        consult.setQuery(content);
        consult.setResult(result);
        consult.setTime(time);
        consultDao.newConsult(consult);
        logger.info("用户{}咨询：{}\n预测结果{}", phone, content, result);
        return result;
    }

    /**
     * @Description 与神经网络计算服务器进行通信
     * source: https://blog.csdn.net/ylf12341/article/details/89890464
     * @param content 智能咨询信息
     * @return String 预测结果
     */
    private String remoteCall(String content) {
        Socket socket = null;
        try {
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("content", content);
            String str = jsonInput.toString();
            // 访问服务进程的套接字
            String HOST = "192.168.137.1";
            Integer PORT = 12345;
            logger.info("调用远程接口:host=>" + HOST + ",port=>" + PORT);

            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket(HOST, PORT);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print(str);
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "gbk"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while ((tmp = br.readLine()) != null)
                sb.append(tmp).append('\n');
            // 解析结果
            String res = sb.toString();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
            }
            logger.info("远程接口调用结束.");
        }
        return null;
    }

    /**
     * #Description 获得罪名的名称
     * @param id 罪名的编号
     * @return 罪名的名称
     */
    public String getAccu(Integer id) {
        String accu = accusationDao.getAccu(id);
        return accu;
    }

}