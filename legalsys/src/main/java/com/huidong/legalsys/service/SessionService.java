package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.dao.DisputeDao;
import com.huidong.legalsys.dao.SessionDao;
import com.huidong.legalsys.dao.UserDao;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.Session;
import com.huidong.legalsys.domain.User;
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
import java.util.ArrayList;

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
    @Autowired
    private DisputeDao disputeDao;

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

    /**
     * @Description 获得会话详情
     * @param id 会话编号
     * @return 会话详情
     */
    public Session getSession(Integer id) {
        Session session = sessionDao.getSessionInfo(id);
        return session;
    }

    /**
     * @Description 获得讨论区所有未建立会话的咨询
     * @return ArrayList<Session> 讨论区所有未建立会话的咨询
     */
    public ArrayList<Session> getAllSessions() {
        ArrayList<Session> sessions = sessionDao.getAllSessions();
        return sessions;
    }

    /**
     * @Description 律师用户主动发起会话
     * @param lawyerphone 律师手机号
     * @param id 讨论区咨询的编号
     */
    @Transactional(noRollbackForClassName = "LegalsysException")
    public void estbConvrLawyer(String lawyerphone, Integer id){
        String isLawyer = userDao.isRegistedLawyer(lawyerphone);
        Integer isExist = sessionDao.isExist(id);
        String phone = sessionDao.getPhone(id);
        if (lawyerphone.equals(phone)) {
            throw new LegalsysException(ErrorEnum.ESTBCONVR_ERROR);
        }
        if (isLawyer == null) {
            throw new LegalsysException(ErrorEnum.NOTLAWYER_ERROR);
        }
        if (isExist == null) {
            throw new LegalsysException(ErrorEnum.SESSIONNOTEXIST_ERROR);
        }
        String content = sessionDao.getContent(id);
        String initConvr = phone + "\t" + content;
        Convr convr = new Convr();
        convr.setPhone(phone);
        convr.setLawyerphone(lawyerphone);
        convr.setConvr(initConvr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        convr.setTime(time);
        convrDao.newConvr(convr);
        sessionDao.setStatus(id, SessionStatusEnum.ESTABLISHED.getStatus());
        logger.info("用户{}和用户{}建立会话", phone, lawyerphone);
    }

    /**
     * @Description 普通用户主动发起会话
     * @param lawyerphone 律师手机号
     * @param user 咨询者的个人信息
     */
    @Transactional(noRollbackForClassName = "LegalsysException")
    public void estbConvr(String lawyerphone, User user){
        String phone = user.getPhone();
        String name = user.getName();
        if (lawyerphone.equals(phone)) {
            throw new LegalsysException(ErrorEnum.ESTBCONVR_ERROR);
        }
        String initConvr = phone + "\t" + "你好！我是" + name + "。我的手机号是" + phone;
        Convr convr = new Convr();
        convr.setPhone(phone);
        convr.setLawyerphone(lawyerphone);
        convr.setConvr(initConvr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        convr.setTime(time);
        convrDao.newConvr(convr);
        logger.info("用户{}和用户{}建立会话", phone, lawyerphone);
    }

    /**
     * @Description 获得所有法律纠纷类型
     * @return ArrayList<String> 所有法律纠纷类型
     */
    public ArrayList<String> getAllCategories() {
        ArrayList<String> allCategories = disputeDao.getAllCategories();
        return allCategories;
    }

    /**
     * @Description 获得某个类别的所有律师信息
     * @param category 类别的名称
     * @return 该类别下所有律师信息
     */
    public ArrayList<User> getlawyersByCategory(String category) {
        ArrayList<User> lawyerByCategory = userDao.getLawyersByCategory(category);
        return lawyerByCategory;
    }
}
