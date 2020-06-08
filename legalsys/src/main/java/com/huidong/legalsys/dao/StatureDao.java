package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Stature;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Description 法条信息表的数据访问类
 */
@Repository
public interface StatureDao {
    /**
     * @Description 法条点击数加一
     * @param lawid 法条编号
     * @return Boolean 设置是否成功
     */
    Boolean addClickrate(Integer lawid);

    /**
     * @Description 获得法条信息
     * @param lawid 法条编号
     * @return Stature 法条信息
     */
    Stature getStatureInfo(Integer lawid);

    /**
     * @Description 获得所有法条信息
     * @return List<Stature> 所有法条信息
     */
    ArrayList<Stature> getAllStatures();

    /**
     * @Description 获得点击数前k的法条信息
     * @return List<Stature> 所有法条信息
     */
    ArrayList<Stature> getTopkStatures();
}
