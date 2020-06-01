package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.LoginLog;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginLogDaoTest extends TestCase {

    @Autowired
    private LoginLogDao loginLogDao;

    @Test
    public void newLoginTest(){
        LoginLog loginLog = new LoginLog();
        loginLog.setPhone("15190218902");
        loginLog.setIp("192.168.137.1");
        loginLog.setAttempt(1);
        loginLog.setStatus(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String datenow = simpleDateFormat.format(date);
        loginLog.setTimeLatest(datenow);
        Boolean isSuccess = loginLogDao.newLogin(loginLog);
        if (!isSuccess)
            System.out.println("newLoginTest:\n" + "login failed ");
        else
            System.out.println("newLoginTest:\n" + "login succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void isLoginedTest(){
        LoginLog loginLog = loginLogDao.isLogined("15190218902");
        if (loginLog == null)
            System.out.println("isLoginedTest:\n" + "login failed ");
        else
            System.out.println("isLoginedTest:\n" + "login succeed " + loginLog);
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setIpTest(){
        String phone = "15190218902";
        String ip = "127.0.0.1";
        Boolean isSuccess = loginLogDao.setIp(phone, ip);
        if (!isSuccess)
            System.out.println("isLoginedTest:\n" + "set ip failed ");
        else
            System.out.println("isLoginedTest:\n" + "set ip succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void addAttemptTest(){
        String phone = "15190218902";
        Boolean isSuccess = loginLogDao.addAttempt(phone);
        if (!isSuccess)
            System.out.println("addAttemptTest:\n" + "add attempt failed ");
        else
            System.out.println("addAttemptTest:\n" + "add attempt succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setAttemptTest(){
        String phone = "15190218902";
        Integer attempt = 0;
        Boolean isSuccess = loginLogDao.setAttempt(phone, attempt);
        if (!isSuccess)
            System.out.println("setAttemptTest:\n" + "set attempt failed ");
        else
            System.out.println("setAttemptTest:\n" + "set attempt succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setStatusTest(){
        String phone = "15190218902";
        Boolean isSuccess = loginLogDao.setStatus(phone, 0);
        if (isSuccess == false)
            System.out.println("setStatusTest:\n" + "set status failed ");
        else
            System.out.println("setStatusTest:\n" + "set status succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void setTimeLatestTest(){
        String phone = "15190218902";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date();
        String datenow = simpleDateFormat.format(date);
        Boolean isSuccess = loginLogDao.setTimeLatest(phone, datenow);
        if (isSuccess == false)
            System.out.println("setTimeLatestTest:\n" + "set timeLatest failed ");
        else
            System.out.println("setTimeLatestTest:\n" + "set timeLatest succeed ");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getAllLoginLogsTest(){
        List<LoginLog> loginLogs = loginLogDao.getAllLoginLogs();
        System.out.println("getAllLoginLogsTest:\n");
        for (LoginLog loginLog : loginLogs){
            System.out.println(loginLog);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Test
    public void getConcurrentUsersTest(){
        Integer userNum = loginLogDao.getConcurrentUsers();
        if (userNum == null)
            System.out.println("getConcurrentUsersTest:\n" + "get concurrent users failed ");
        else
            System.out.println("getConcurrentUsersTest:\n" + "concurrent users num " + userNum);
        System.out.println("-------------------------------------------------------------------------------");
    }
}