package com.huidong.legalsys.service;

import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest extends TestCase {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private LoginService loginService;

    @Test
    public void registerTest() {
        String phone1 = "15190218902";
        String name1 = "张惠东";
        String password1 = "111111";
        String idno1 = "320283199903064812";
        String phone2 = "15190218903";
        String name2 = "张惠西";
        String password2 = "222222";
        String idno2 = "320283199903064811";


        loginService.register(phone2, name2, password2, idno2);
        loginService.register(phone1, name1, password1, idno1);
    }

    @Test
    public void registerLawyerTest(){
        String phone1 = "15190218901";
        String name1 = "张惠南";
        String password1 = "111111";
        String idno1 = "320283199903064810";
        String licenseurl1 = "url1";
        String firmname1 = "firmname1";

        String phone2 = "15190218904";
        String name2 = "张惠北";
        String password2 = "222222";
        String idno2 = "320283199903064813";
        String licenseurl2 = "url2";
        String firmname2 = "firmname2";

        loginService.registerLawyer(phone1, name1, password1, idno1, licenseurl1, firmname1);
        loginService.registerLawyer(phone2, name2, password2, idno2, licenseurl2, firmname2);
    }

    @Test
    public void loginTest(){
        String phone1 = "15190218902";
        String password1 = "111111";
        String phone2 = "15190218901";
        String password2 = "222222";

        //loginService.login(phone1, password1);
        loginService.login(phone2, password2);
    }
}