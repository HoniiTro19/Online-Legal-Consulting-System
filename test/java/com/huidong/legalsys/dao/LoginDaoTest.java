package com.huidong.legalsys.dao;

import com.huidong.legalsys.domain.Login;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @Description  junit进行对数据访问层LoginDao的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginDaoTest extends TestCase {

    @Autowired
    private LoginDao loginDao;

    @Test
    public void newLoginTest(){
        Login login = new Login();
        login.setPhone("11111111111");
        login.setAttempt(1);
        login.setStatus(1);
        Boolean isSuccess = loginDao.newLogin(login);
        assertTrue("新建登录记录失败", isSuccess);
    }

    @Test
    public void isLoginedTest(){
        Login login = loginDao.isLogined("11111111111");
        assertNotNull("该用户还未尝试登录过", login);
    }

    @Test
    public void addAttemptTest(){
        String phone = "11111111111";
        Boolean isSuccess = loginDao.addAttempt(phone);
        assertTrue("未能增加失败次数", isSuccess);
    }

    @Test
    public void getAttemptTest(){
        String phone = "11111111111";
        Integer attempt = loginDao.getAttempt(phone);
        assertNotNull("未能获取失败次数", attempt);
    }

    @Test
    public void resetAttemptTest(){
        String phone = "11111111111";
        Boolean isSuccess = loginDao.resetAttempt(phone);
        assertTrue("未能重置失败次数", isSuccess);
    }

    @Test
    public void setStatusTest(){
        String phone = "11111111111";
        Boolean isSuccess = loginDao.setStatus(phone, 0);
        assertTrue("未能设置用户登录状态", isSuccess);
    }

    @Test
    public void getStatusTest(){
        String phone = "11111111111";
        Integer status = loginDao.getStatus(phone);
        assertNotNull("未能获取用户登录状态", status);
    }

    @Test
    public void setFreezetimeTest(){
        String phone = "11111111111";
        Boolean isSuccess = loginDao.setFreezetime(phone, " ");
        assertTrue("未能设置用户上次失败登录的时间", isSuccess);
    }

    @Test
    public void getFreezetimeTest(){
        String phone = "11111111111";
        String freezetime = loginDao.getFreezetime(phone);
        assertNotNull("未能获取用户上次失败登录的时间", freezetime);
    }
}