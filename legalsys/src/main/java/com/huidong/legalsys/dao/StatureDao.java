package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Stature;
import org.springframework.stereotype.Repository;

import java.util.List;

/* 法条信息表的数据访问类
 * initCommit 初始化法条信息库
 *  @param: staturexml xml格式法律条文的本地地址
 *
 * addClickrate 增加法律的点击数
 *  @param: lawid, addNum 法条编号，点击数增加量
 *
 * getStature 获得单一法条信息
 *  @param: lawid 法条编号
 *  @return: Stature 法条信息
 *
 * getAllStatures 获得所有法条信息
 *  @return 法条信息列表
 *
 * getTopkStatures 获得点击率前k的法条信息
 *  @param: topk k值
 *  @return: 法条信息列表
 */

@Repository
public interface StatureDao {
    void addClickrate(Integer lawid, Integer addNum);
    Stature getStature(Integer lawid);
    List<Stature> getAllStatures();
    List<Stature> getTopkStatures();
}
