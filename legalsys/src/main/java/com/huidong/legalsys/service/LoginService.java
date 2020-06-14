package com.huidong.legalsys.service;

import com.huidong.legalsys.domain.Login;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.dao.LoginDao;
import com.huidong.legalsys.dao.UserDao;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.enumeration.LoginStatusEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @Description 用户登录的业务逻辑层
 */
@Service
public class LoginService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Value("${config.pattern}")
    private String pattern;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginDao loginDao;

    /**
     * @param phone    手机号
     * @param name     真实姓名
     * @param password 密码
     * @param idno     身份证号
     * @Description 对未注册的普通用户进行注册
     */
    public void register(String phone, String name, String password, String idno) {
        String username = userDao.isRegisted(phone);
        if (username != null) {
            throw new LegalsysException(ErrorEnum.REGISTER_ERROR);
        }
        String isIdno = userDao.getIdno(idno);
        if (isIdno != null) {
            throw new LegalsysException(ErrorEnum.IDNO_ERROR);
        }
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setPassword(password);
        user.setIdno(idno);
        userDao.register(user);
        logger.info("注册新的普通用户: {}", user);
    }

    /**
     * @param phone      手机号
     * @param name       真实姓名
     * @param password   密码
     * @param idno       身份证号
     * @param licenseurl 律师执照地址
     * @param firmname   律所信息
     * @Description 对未注册的律师用户进行注册
     */
    public void registerLawyer(String phone, String name, String password, String idno, String licenseurl, String firmname) {
        String username = userDao.isRegisted(phone);
        if (username != null) {
            throw new LegalsysException(ErrorEnum.REGISTER_ERROR);
        }
        String isIdno = userDao.getIdno(idno);
        if (isIdno != null) {
            throw new LegalsysException(ErrorEnum.IDNO_ERROR);
        }
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setPassword(password);
        user.setIdno(idno);
        user.setLicenseurl(licenseurl);
        user.setFirmname(firmname);
        userDao.register(user);
        logger.info("注册新的律师用户: {}", user);
    }

    /**
     * @param phone    手机号
     * @param password 密码
     * @return User 用户个人信息
     * @Description 考虑异常处理的用户登录
     */
    @Transactional(noRollbackForClassName = "LegalsysException")
    public User login(String phone, String password) {
        String username = userDao.isRegisted(phone);
        if (username == null) {
            throw new LegalsysException(ErrorEnum.NOTREGISTER_ERROR);
        }

        Login login = loginDao.isLogined(phone);
        if (login == null) {
            Login newlogin = new Login();
            newlogin.setPhone(phone);
            newlogin.setAttempt(0);
            newlogin.setStatus(LoginStatusEnum.NOTFREEZE.getStatus());
            newlogin.setFreezeTime(null);
            loginDao.newLogin(newlogin);
            logger.info("用户{}首次尝试登录", phone);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        String freezetime = loginDao.getFreezetime(phone);
        if (freezetime != null) {
            Long timenow = date.getTime();
            Long timeLatest = 0L;
            try {
                timeLatest = format.parse(freezetime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (timenow - timeLatest > 6 * 60 * 1000) {
                loginDao.resetAttempt(phone);
                loginDao.setStatus(phone, LoginStatusEnum.NOTFREEZE.getStatus());
                loginDao.setFreezetime(phone, null);
                logger.info("用户{}账号登录信息恢复正常", phone);
            }
        }

        Integer status = loginDao.getStatus(phone);
        if (status.equals(LoginStatusEnum.FREEZE.getStatus())) {
            logger.info("用户{}连续5次登录失败，冻结账号10分钟，请稍后再登录", phone);
            throw new LegalsysException(ErrorEnum.FREEZE_ERROR);
        }

        User user = userDao.login(phone, password);
        if (user == null) {
            loginDao.addAttempt(phone);
            loginDao.setFreezetime(phone, format.format(date));
            if (loginDao.getAttempt(phone) > 4) {
                loginDao.setStatus(phone, LoginStatusEnum.FREEZE.getStatus());
                logger.info("用户{}连续5次登录失败，冻结账号10分钟", phone);
            }
            throw new LegalsysException(ErrorEnum.PASSWORD_ERROR);
        } else {
            loginDao.setStatus(phone, LoginStatusEnum.NOTFREEZE.getStatus());
            loginDao.setFreezetime(phone, null);
            loginDao.resetAttempt(phone);
            logger.info("用户{}登录成功", phone);
            logger.info("用户信息为{}", user);
            return user;
        }
    }
}