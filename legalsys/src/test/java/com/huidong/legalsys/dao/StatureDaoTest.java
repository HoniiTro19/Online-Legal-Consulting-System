package com.huidong.legalsys.dao;

/*
 * junit进行对数据访问层StatureDao的单元测试
 */

import com.huidong.legalsys.domain.Stature;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StatureDaoTest extends TestCase {

    @Autowired
    private StatureDao statureDao;
    @Value("#{systemProperties['config.statureurl']}")
    private String staturexml;
    @Value("#{systemProperties['config.topk']}")
    private Integer topk;

    @Test
    public void initCommitTest(){
        statureDao.initCommit(staturexml);
        System.out.println("initCommitTest:\n" + "initialize ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void addClickrateTest(){
        Integer lawid = 100;
        Integer addNum = 1;
        statureDao.addClickrate(lawid, addNum);
        System.out.println("addClickrateTest:\n" + "add num ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getStatureTest(){
        Integer lawid = 99;
        Stature stature = statureDao.getStature(lawid);
        System.out.println("getStatureTest:\n" + stature);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllStaturesTest(){
        List<Stature> statures = statureDao.getAllStatures();
        System.out.println("getAllStaturesTest:\n");
        for (Stature stature : statures){
            System.out.println(stature);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getTopkStaturesTest(){
        List<Stature> statures = statureDao.getTopkStatures(topk);
        System.out.println("getTopkStaturesTest:\n");
        for (Stature stature : statures){
            System.out.println(stature);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
}