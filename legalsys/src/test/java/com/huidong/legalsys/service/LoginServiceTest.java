package com.huidong.legalsys.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description junit进行对数据访问层LoginService的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest extends TestCase {

    @Autowired
    private LoginService loginService;

    @Test
    public void registerTest() {
        String phone1 = "11111111112";
        String name1 = "张";
        String password1 = "111111";
        String idno1 = "111111111111111112";
        String phone2 = "11111111113";
        String name2 = "惠";
        String password2 = "111111";
        String idno2 = "111111111111111113";

        loginService.register(phone1, name1, password1, idno1);
        loginService.register(phone2, name2, password2, idno2);
    }

    @Test
    public void registerLawyerTest(){
        String phone1 = "11111111114";
        String name1 = "东";
        String password1 = "111111";
        String idno1 = "111111111111111114";
        String licenseurl1 = "url1";
        String firmname1 = "firmname1";

        String phone2 = "11111111115";
        String name2 = "西";
        String password2 = "111111";
        String idno2 = "111111111111111115";
        String licenseurl2 = "url2";
        String firmname2 = "firmname2";

//        loginService.registerLawyer(phone1, name1, password1, idno1, licenseurl1, firmname1);
//        loginService.registerLawyer(phone2, name2, password2, idno2, licenseurl2, firmname2);
    }

    @Test
    public void loginTest(){
        String phone1 = "11111111112";
        String password1 = "111111";
        String phone2 = "11111111113";
        String password2 = "222222";

        loginService.login(phone1, password1);
        loginService.login(phone2, password2);
    }
}