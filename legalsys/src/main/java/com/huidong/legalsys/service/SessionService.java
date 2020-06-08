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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description 讨论区咨询的业务逻辑层
 */
@Service
public class SessionService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Value("${config.pattern}")
    private String pattern;
    @Autowired
    private SessionDao sessionDao;
    @Autowired
    private ConvrDao convrDao;
    @Autowired
    private UserDao userDao;

    /**
     * @Description 新建讨论区咨询
     * @param phone 手机号
     * @param title 咨询标题
     * @param content 咨询内容
     */
    public void newsession(String phone, String title, String content){
        Session session = new Session();
        session.setPhone(phone);
        session.setTitle(title);
        session.setContent(content);
        session.setStatus(SessionStatusEnum.UNESTABLISHED.getStatus());
        sessionDao.newSession(session);
        logger.info("新的咨询已发布：{}", title);
    }

    public Session getSession(Integer id) {
        Session session = sessionDao.getSession(id);
        return session;
    }

    /**
     * @Description 获得讨论区所有未建立会话的咨询
     * @return List<Session> 讨论区所有未建立会话的咨询
     */
    public List<Session> getAllSessions() {
        List<Session> sessions = sessionDao.getAllSessions();
        return sessions;
    }

    /**
     * @Description 为用户新建立会话
     * @param lawyerphone 律师手机号
     * @param id 普通用户手机号
     */
    @Transactional(noRollbackForClassName = "LegalsysException")
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
        convr.setLawyerconvr("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        convr.setTime(time);
        convr.setLawyertime("");
        convrDao.newConvr(convr);
        sessionDao.setStatus(id, SessionStatusEnum.ESTABLISHED.getStatus());
        logger.info("用户{}和用户{}建立会话", phone, lawyerphone);
    }
}
