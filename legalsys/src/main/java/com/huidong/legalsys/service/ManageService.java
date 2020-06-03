package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.dao.UserDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private ConvrDao convrDao;

    public void changePassword(String phone, String oldpassword, String newpassword){
        String password = userDao.getPassword(phone);
        if (oldpassword.equals(password)){
            userDao.setPassword(phone, newpassword);
            logger.info("用户{}成功修改了密码", phone);
        }else {
            throw new LegalsysException(ErrorEnum.PASSWORD_ERROR);
        }
    }

    public void lawyerAuth(String phone, String licenseurl, String firmname){
        if (licenseurl != null && firmname != null){
            userDao.setLicenseurl(phone, licenseurl);
            userDao.setFirmname(phone, firmname);
            logger.info("用户{}成功修改律师认证信息", phone);
        }else {
            throw new LegalsysException(ErrorEnum.AUTH_ERROR);
        }
    }

    public List<Consult> getConsultsByPhone(String phone){
        List<Consult> consults = consultDao.getConsultsByPhone(phone);
        logger.info("用户{}查询自己的咨询记录", phone);
        return consults;
    }

    public void deleteConsult(Integer id){
        consultDao.deleteConsult(id);
        logger.info("咨询记录{}被删除了", id);
    }

    public List<Convr> getConvrs(String phone){
        String isLawyer = userDao.isRegistedLawyer(phone);
        List<Convr> convrs;
        if (isLawyer == null){
            convrs = convrDao.getConvrsByPhone(phone);
        }else {
            convrs = convrDao.getConvrsByLawyerPhone(phone);
        }
        logger.info("用户{}查询了自己的会话记录", phone);
        return convrs;
    }

    public void deleteConvr(Integer id){
        convrDao.deleteConvr(id);
        logger.info("会话记录{}被删除了", id);
    }

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
    }
}
