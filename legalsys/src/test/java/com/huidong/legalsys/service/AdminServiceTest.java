package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description junit进行对数据访问层AdminService的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminServiceTest extends TestCase {

    @Autowired
    private AdminService adminService;

    @Test
    public void getAllUsersTest() {
        adminService.getAllUsers();
    }

    @Test
    public void getAllStaturesTest() {
        adminService.getAllStatures();
    }

    @Test
    public void getAllConsultsTest() {
        adminService.getAllConsults();
    }

    @Test
    public void getAllConvrsTest() {
        adminService.getAllConvrs();
    }
}