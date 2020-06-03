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

    public List<User> getAllUsers(){
        List<User> users = userDao.getAllUsers();
        return users;
    }

    public List<Stature> getAllStatures(){
        List<Stature> statures = statureDao.getAllStatures();
        return statures;
    }

    public List<Consult> getAllConsults(){
        List<Consult> consults = consultDao.getAllConsults();
        return consults;
    }

    public List<Convr> getAllConvrs(){
        List<Convr> convrs = convrDao.getAllConvrs();
        return convrs;
    }
}
