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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

@Service
public class ConsultService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private StatureDao statureDao;
    @Value("#{systemProperties['config.lawPython']}")
    String lawPythonPath;
    @Value("#{systemProperties['config.penaltyPython']}")
    String penaltyPythonPath;

    public Stature getStature(Integer lawid){
        Stature stature = statureDao.getStature(lawid);
        if (stature == null){
            throw new LegalsysException(ErrorEnum.LAWNOTFOUND_ERROOR);
        }else {
            statureDao.addClickrate(lawid);
            return stature;
        }
    }

    public String newconsult(String phone, String query, Integer type) throws IOException {
        String[] args;
        String result;
        if (type.equals(0)) {
            args = new String[]{"python", lawPythonPath, query};
        } else {
            args = new String[]{"python", penaltyPythonPath, query};
        }
        Process pr = Runtime.getRuntime().exec(args);
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        LineNumberReader input = new LineNumberReader(in);
        result = input.readLine();
        input.close();
        in.close();
        logger.info("用户{}咨询：{}\n预测结果{}", phone, query, result);
        return result;
    }
}
