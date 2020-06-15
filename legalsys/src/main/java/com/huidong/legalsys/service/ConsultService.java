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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 用户咨询的业务逻辑层
 */
@Service
public class ConsultService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Value("${config.predictPython}")
    String predictPythonPath;
    @Value("${config.pytorch}")
    String pytorchPath;
    @Value("${config.pattern}")
    String pattern;
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
     * @return String 智能预测详情
     * @throws IOException
     */
    public String newconsult(String phone, String title, String query) {
        String[] args;
        String result = "";
        args = new String[]{pytorchPath, predictPythonPath, query};
        try {
            Process pr = Runtime.getRuntime().exec(args);
            InputStreamReader in = new InputStreamReader(pr.getInputStream());
            LineNumberReader input = new LineNumberReader(in);
            result = input.readLine();
            input.close();
            in.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        String time = format.format(date);
        Consult consult = new Consult();
        consult.setPhone(phone);
        consult.setTitle(title);
        consult.setQuery(query);
        consult.setResult(result);
        consult.setTime(time);
        consultDao.newConsult(consult);
        logger.info("用户{}咨询：{}\n预测结果{}", phone, query, result);
        return result;
    }
}
