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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class LoginService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginDao loginDao;

    public void register(String phone, String name, String password, String idno) throws LegalsysException{
        String username = userDao.isRegisted(phone);
        if (username != null){
            throw new LegalsysException(ErrorEnum.REGISTER_ERROR);
        }else {
            User user = new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            user.setIdno(idno);
            userDao.register(user);
            logger.info("注册新的用户: {}", phone);
        }
    }

    public void registerLawyer(String phone, String name, String password, String idno, String licenseurl, String firmname){
        String username = userDao.isRegistedLawyer(phone);
        if (username != null){
            throw new LegalsysException(ErrorEnum.REGISTER_ERROR);
        }else {
            User user = new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            user.setIdno(idno);
            user.setLicenseurl(licenseurl);
            user.setFirmname(firmname);
            userDao.register(user);
            logger.info("注册新的用户: {}", user);
        }
    }

    public User login(String phone, String password) throws ParseException {
        String username = userDao.isRegisted(phone);
        if (username == null){
            throw new LegalsysException(ErrorEnum.NOTREGISTER_ERROR);
        }
        Login login = loginDao.isLogined(phone);
        if (login == null){
            Login newlogin = new Login();
            newlogin.setPhone(phone);
            newlogin.setAttempt(0);
            newlogin.setStatus(LoginStatusEnum.OFFLINE.getStatus());
            newlogin.setFreezeTime(null);
            loginDao.newLogin(newlogin);
            logger.info("用户{}首次登录", phone);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String freezetime = loginDao.getFreezetime(phone);
        if (loginDao.getStatus(phone).equals(1) && freezetime != null &&
                (new Date().getTime() - format.parse(freezetime).getTime() > 6*60*1000)){
            loginDao.setStatus(phone, LoginStatusEnum.OFFLINE.getStatus());
            loginDao.setFreezetime(phone, null);
            logger.info("用户{}账号解封", phone);
        }
        User user =userDao.login(phone, password);
        if (user == null){
            loginDao.addAttempt(phone);
            if (loginDao.getAttempt(phone) > 4){
                loginDao.setStatus(phone, LoginStatusEnum.FREEZE.getStatus());
                loginDao.setFreezetime(phone, format.format(new Date()));
                logger.info("用户{}连续5次登录失败，冻结账号10分钟，请稍后再登录", phone);
                throw new LegalsysException(ErrorEnum.FREEZE_ERROR);
            }
            throw new LegalsysException(ErrorEnum.PASSWORD_ERROR);
        }else {
            logger.info("用户{}登录成功", phone);
            logger.info("用户信息为{}", user);
            return user;
        }

    }
}
