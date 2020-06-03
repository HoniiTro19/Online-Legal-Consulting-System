package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.dao.SessionDao;
import com.huidong.legalsys.dao.UserDao;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.Session;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.enumeration.SessionStatusEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SessionService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private ConvrDao convrDao;
    @Autowired
    private UserDao userDao;

    public void newsession(String phone, String title, String content, Integer status){
        Session session = new Session();
        session.setPhone(phone);
        session.setTitle(title);
        session.setContent(content);
        session.setStatus(status);
        sessionDao.newSession(session);
        logger.info("新的咨询已发布：{}", title);
    }

    public void estbConvr(String lawyerphone, Integer id){
        String isLawyer = userDao.isRegistedLawyer(lawyerphone);
        Integer isExist = sessionDao.isExist(id);
        if (isLawyer == null){
            throw new LegalsysException(ErrorEnum.NOTLAWYER_ERROR);
        }
        if (isExist == null){
            throw new LegalsysException(ErrorEnum.SESSIONNOTEXIST_ERROR);
        }
        String phone = sessionDao.getPhone(id);
        String title = sessionDao.getTitle(id);
        String content = sessionDao.getContent(id);
        String initConvr = title + "\n" + content;
        Convr convr = new Convr();
        convr.setPhone(phone);
        convr.setLawyerphone(lawyerphone);
        convr.setConvr(initConvr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        convr.setTime(time);
        convrDao.newConvr(convr);
        sessionDao.setStatus(id, SessionStatusEnum.ESTABLISHED.getStatus());
        logger.info("用户{}和用户{}建立会话", phone, lawyerphone);
    }
}
