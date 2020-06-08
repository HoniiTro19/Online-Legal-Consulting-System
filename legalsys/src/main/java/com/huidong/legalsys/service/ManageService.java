package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.dao.UserDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * @Description 用户管理的业务逻辑层
 */
@Service
public class ManageService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private ConvrDao convrDao;

    /**
     * @Description 修改用户的密码
     * @param phone 手机号
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     */
    public void changePassword(String phone, String oldpassword, String newpassword){
        String password = userDao.getPassword(phone);
        if (oldpassword.equals(password)){
            userDao.setPassword(phone, newpassword);
            logger.info("用户{}成功修改了密码", phone);
        }else {
            throw new LegalsysException(ErrorEnum.PASSWORD_ERROR);
        }
    }

    /**
     * @Description 修改律师认证信息
     * @param phone 手机号
     * @param licenseurl 律师执照地址
     * @param firmname 律所信息
     */
    @Transactional(rollbackForClassName = "RuntimeException")
    public void lawyerAuth(String phone, String licenseurl, String firmname){
        if (licenseurl != null && firmname != null){
            userDao.setLicenseurl(phone, licenseurl);
            userDao.setFirmname(phone, firmname);
            logger.info("用户{}成功修改律师认证信息", phone);
        }else {
            throw new LegalsysException(ErrorEnum.AUTH_ERROR);
        }
    }

    /**
     * @Description 用户查询自己的咨询记录
     * @param phone 手机号
     * @return ArrayList<Consult> 用户的咨询记录
     */
    public ArrayList<Consult> getConsultsByPhone(String phone){
        ArrayList<Consult> consults = consultDao.getConsultsByPhone(phone);
        return consults;
    }

    /**
     * @Description 删除咨询记录
     * @param id 咨询编号
     */
    public void deleteConsult(Integer id){
        consultDao.deleteConsult(id);
        logger.info("咨询记录{}被删除了", id);
    }

    /**
     * @Description 用户查询自己的会话记录
     * @param phone 手机号
     * @return ArrayList<Convr> 用户的会话记录
     */
    public ArrayList<Convr> getConvrs(String phone){
        String isLawyer = userDao.isRegistedLawyer(phone);
        ArrayList<Convr> convrs = new ArrayList<>();
        if (isLawyer == null){
            convrs = convrDao.getConvrsByPhone(phone);
        }else {
            convrs = convrDao.getConvrsByLawyerPhone(phone);
        }
        return convrs;
    }

    /**
     * @Description 删除会话
     * @param id 会话编号
     */
    public void deleteConvr(Integer id){
        convrDao.deleteConvr(id);
        logger.info("会话记录{}被删除了", id);
    }

    /**
     * @Description 用户在会话中发送消息
     * @param phone 手机号
     * @param record 消息内容
     * @param recordtime 消息时间
     * @param id 会话编号
     */
    @Transactional(rollbackForClassName = "RuntimeException")
    public void contConvr(String phone, String record, String recordtime, Integer id){
        String isLawyer = userDao.isRegistedLawyer(phone);
        if (isLawyer == null){
            String convr = convrDao.getConvr(id);
            String time = convrDao.getTime(id);
            String newconvr = convr + "\n" + record;
            String newtime = time + "\n" + recordtime;
            convrDao.setConvr(newconvr, id);
            convrDao.setTime(newtime, id);
        }else {
            String convr = convrDao.getLawyerConvr(id);
            String time = convrDao.getLawyerTime(id);
            String newconvr = convr + "\n" + record;
            String newtime = time + "\n" + recordtime;
            convrDao.setLawyerConvr(newconvr, id);
            convrDao.setLawyerTime(newtime, id);
        }
        logger.info("用户{}更新了会话消息\n消息内容：{}", phone, record);
    }


    /**
     * @Description 获得用户的基本信息
     * @param phone 手机号
     * @return User 用户的基本信息
     */
    public User getUserInfo(String phone) {
        User userInfo = userDao.getUserInfo(phone);
        return userInfo;
    }
    /**
     * @Description 获得所有注册用户信息
     * @return ArrayList<User> 所有注册用户信息
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = userDao.getAllUsers();
        return users;
    }

    /**
     * @Description 获得所有咨询记录
     * @return ArrayList<Consult> 所有咨询记录
     */
    public ArrayList<Consult> getAllConsults(){
        ArrayList<Consult> consults = consultDao.getAllConsults();
        return consults;
    }

    /**
     * @Description 获得所有会话记录
     * @return ArrayList<Convr> 所有会话记录
     */
    public ArrayList<Convr> getAllConvrs(){
        ArrayList<Convr> convrs = convrDao.getAllConvrs();
        return convrs;
    }
}
