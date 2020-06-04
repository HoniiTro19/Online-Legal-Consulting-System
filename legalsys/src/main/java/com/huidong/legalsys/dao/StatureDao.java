package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Stature;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 法条信息表的数据访问类
 */

@Repository
public interface StatureDao {
    /**
     * addClickrate 法条点击数加一
     * @param lawid 法条编号
     * @return Boolean 设置是否成功
     */
    Boolean addClickrate(Integer lawid);

    /**
     * getStature 获得法条信息
     * @param lawid 法条编号
     * @return Stature 法条信息
     */
    Stature getStature(Integer lawid);

    /**
     * getAllStatures 获得所有法条信息
     * @return List<Stature> 所有法条信息
     */
    List<Stature> getAllStatures();

    /**
     * getTopkStatures 获得点击数前k的法条信息
     * @return List<Stature> 所有法条信息
     */
    List<Stature> getTopkStatures();
}
