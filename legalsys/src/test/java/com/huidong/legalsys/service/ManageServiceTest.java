package com.huidong.legalsys.service;

import com.huidong.legalsys.handle.ExceptionHandle;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ManageServiceTest extends TestCase {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private ManageService manageService;

    @Test
    public void changePasswordTest(){
        String phone = "15190218902";
        String oldpassword = "111111";
        String newpassword = "222222";
        manageService.changePassword(phone, oldpassword, newpassword);
    }

    @Test
    public void lawyerAuthTest(){
        String phone = "15190218902";
        String licenseurl = "url1";
        String firmname = "firmname1";
        manageService.lawyerAuth(phone, licenseurl, firmname);
    }

}