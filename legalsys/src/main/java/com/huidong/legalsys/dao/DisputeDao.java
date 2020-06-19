package com.huidong.legalsys.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Description 法律纠纷类型的数据访问类
 */
@Repository
public interface DisputeDao {

    /**
     * @Description 获得所有法律纠纷类型
     * @return ArrayList<String> 所有法律纠纷类型
     */
     ArrayList<String> getAllCategories();
}
