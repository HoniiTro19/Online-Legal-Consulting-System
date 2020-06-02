package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Login;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginDaoTest extends TestCase {

    @Autowired
    private LoginDao loginDao;

    @Test
    public void newLoginTest(){
        Login login = new Login();
        login.setPhone("15190218902");
        login.setAttempt(1);
        login.setStatus(1);
        Boolean isSuccess = loginDao.newLogin(login);
        if (!isSuccess)
            System.out.println("newLoginTest:\n" + "login failed ");
        else
            System.out.println("newLoginTest:\n" + "login succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void isLoginedTest(){
        Login login = loginDao.isLogined("15190218902");
        if (login == null)
            System.out.println("isLoginedTest:\n" + "login failed ");
        else
            System.out.println("isLoginedTest:\n" + "login succeed " + login);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void addAttemptTest(){
        String phone = "15190218902";
        Boolean isSuccess = loginDao.addAttempt(phone);
        if (!isSuccess)
            System.out.println("addAttemptTest:\n" + "add attempt failed ");
        else
            System.out.println("addAttemptTest:\n" + "add attempt succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setStatusTest(){
        String phone = "15190218902";
        Boolean isSuccess = loginDao.setStatus(phone, 0);
        if (isSuccess == false)
            System.out.println("setStatusTest:\n" + "set status failed ");
        else
            System.out.println("setStatusTest:\n" + "set status succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllLoginsTest(){
        List<Login> logins = loginDao.getAllLogins();
        System.out.println("getAllLoginLogsTest:\n");
        for (Login login : logins){
            System.out.println(login);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConcurrentUsersTest(){
        Integer userNum = loginDao.getConcurrentUsers();
        if (userNum == null)
            System.out.println("getConcurrentUsersTest:\n" + "get concurrent users failed ");
        else
            System.out.println("getConcurrentUsersTest:\n" + "concurrent users num " + userNum);
        System.out.println("-------------------------------------------------------------------------------");
    }
}