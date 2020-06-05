package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description 用户管理的业务逻辑层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ManageServiceTest extends TestCase {

    @Autowired
    private ManageService manageService;

    @Test
    public void changePasswordTest() {
        String phone = "11111111112";
        String oldpassword = "111111";
        String newpassword = "222222";
        manageService.changePassword(phone, oldpassword, newpassword);
    }

    @Test
    public void lawyerAuthTest() {
        String phone = "11111111111";
        String licenseurl = "url1";
        String firmname = "firmname1";
        manageService.lawyerAuth(phone, licenseurl, firmname);
    }

    @Test
    public void getConsultsByPhoneTest() {
        String phone = "11111111111";
        manageService.getConsultsByPhone(phone);
    }

    @Test
    public void deleteConsultTest() {
        Integer id = 1;
        manageService.deleteConsult(id);
    }

    @Test
    public void getConvrsTest() {
        String phone = "11111111111";
        manageService.getConvrs(phone);
    }

    @Test
    public void deleteConvrTest() {
        Integer id = 1;
        manageService.deleteConvr(id);
    }

    @Test
    public void contConvrTest() {
        String phone = "11111111111";
        String record = "你好";
        String recordtime = "now";
        Integer id = 2;
        manageService.contConvr(phone, record, recordtime, id);
    }
}