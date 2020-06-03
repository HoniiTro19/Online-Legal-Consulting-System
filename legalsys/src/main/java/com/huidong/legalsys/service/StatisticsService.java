package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.LoginDao;
import com.huidong.legalsys.dao.StatureDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private StatureDao statureDao;
    @Autowired
    private ConsultDao consultDao;

    public Integer showConcurrent(){
        Integer usernum = loginDao.getConcurrentUsers();
        logger.info("当前在线人数为：{}", usernum);
        return usernum;
    }

    public List<Stature> rcmdStature(){
        List<Stature> statures = statureDao.getTopkStatures();
        return statures;
    }

    public List<Consult> rcmdConsults(){
        List<Consult> consults = consultDao.getTopkConsults();
        return consults;
    }
}
