package com.huidong.legalsys.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description 罪名表的数据访问类
 */
@Repository
public interface AccusationDao {

    /**
     * @Description 获得罪名的名称
     * @param id 罪名的编号
     * @return String 罪名的名称
     */
    String getAccu(Integer id);
}
