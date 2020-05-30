package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Stature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatureDao {
    void initCommit(String staturexml);
    void addClickrate(Integer lawid, Integer addNum);
    Stature getStature(Integer lawid);
    List<Stature> getAllStatures();
    List<Stature> getTopkStatures(Integer topk);
}
