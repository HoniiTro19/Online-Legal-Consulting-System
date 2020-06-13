package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * @Description 用户咨询的业务逻辑层
 */
@Service
public class ConsultService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Value("${config.lawPython}")
    String lawPythonPath;
    @Value("${config.penaltyPython}")
    String penaltyPythonPath;
    @Value("${config.pytorch}")
    String pytorchPath;
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private ConvrDao convrDao;

    /**
     * @Description 获得咨询信息
     * @param id 咨询编号
     * @return Consult 咨询信息
     */
    public Consult getConsult(Integer id) {
        Consult consult = consultDao.getConsultInfo(id);
        return consult;
    }

    /**
     * @Description 获得会话信息
     * @param id 会话编号
     * @return Consult 会话信息
     */
    public Convr getConvr(Integer id) {
        Convr convr = convrDao.getConvrInfo(id);
        return convr;
    }

    /**
     * @Description 新建用户咨询
     * @param phone 手机号
     * @param query 咨询内容
     * @param type 咨询类型
     *  @see com.huidong.legalsys.enumeration.ConsultTypeEnum
     * @return String 智能预测结果
     * @throws IOException
     */
    public String newconsult(String phone, String title, String query, Integer type) throws IOException {
        String[] args;
        String result;
        if (type.equals(0)) {
            args = new String[]{pytorchPath, lawPythonPath, query};
        } else {
            args = new String[]{pytorchPath, penaltyPythonPath, query};
        }
        Process pr = Runtime.getRuntime().exec(args);
        InputStreamReader in = new InputStreamReader(pr.getInputStream());
        LineNumberReader input = new LineNumberReader(in);
        result = input.readLine();
        input.close();
        in.close();
        logger.info("用户{}咨询：{}\n预测结果{}", phone, query, result);
        return result;
    }
}
