package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.StatureDao;
import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.dao.ConsultDao;
import com.huidong.legalsys.dao.ConvrDao;
import com.huidong.legalsys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 管理员后台管理的业务逻辑层
 */
@Service
public class AdminService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StatureDao statureDao;
    @Autowired
    private ConsultDao consultDao;
    @Autowired
    private ConvrDao convrDao;

    /**
     * @Description 获得所有注册用户信息
     * @return List<User> 所有注册用户信息
     */
    public List<User> getAllUsers(){
        List<User> users = userDao.getAllUsers();
        return users;
    }

    /**
     * @Description 获得所有咨询记录
     * @return List<Consult> 所有咨询记录
     */
    public List<Consult> getAllConsults(){
        List<Consult> consults = consultDao.getAllConsults();
        return consults;
    }

    /**
     * @Description 获得所有会话记录
     * @return List<Convr> 所有会话记录
     */
    public List<Convr> getAllConvrs(){
        List<Convr> convrs = convrDao.getAllConvrs();
        return convrs;
    }
}
