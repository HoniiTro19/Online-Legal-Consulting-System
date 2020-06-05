package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.StatureDao;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
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
    private StatureDao statureDao;

    /**
     * @Description 获得法律条文信息
     * @param lawid 法条编号
     * @return Stature 法条信息
     */
    public Stature getStature(Integer lawid){
        Stature stature = statureDao.getStature(lawid);
        if (stature == null){
            throw new LegalsysException(ErrorEnum.LAWNOTFOUND_ERROOR);
        }else {
            statureDao.addClickrate(lawid);
            logger.info("法条{}点击数增加", lawid);
            return stature;
        }
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
    public String newconsult(String phone, String query, Integer type) throws IOException {
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