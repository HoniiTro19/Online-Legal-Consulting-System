package com.huidong.legalsys.service;

import com.huidong.legalsys.dao.StatureDao;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PenallawService {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private StatureDao statureDao;

    /**
     * @Description 获得法律条文信息
     * @param lawid 法条编号
     * @return Stature 法条信息
     */
    public Stature getStature(Integer lawid) {
        Stature stature = statureDao.getStatureInfo(lawid);
        if (stature == null){
            throw new LegalsysException(ErrorEnum.LAWNOTFOUND_ERROOR);
        }else {
            statureDao.addClickrate(lawid);
            logger.info("法条{}点击数增加", lawid);
            return stature;
        }
    }



    /**
     * @Description 获得所有法条信息
     * @return ArrayList<Stature> 所有法条信息
     */
    public ArrayList<Stature> getAllStatures(){
        ArrayList<Stature> statures = statureDao.getAllStatures();
        return statures;
    }
}
