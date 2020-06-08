package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * @Description 用户注册登录的控制层
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Value("${file.upload-dir}")
    private String fileLocation;

    /**
     * @Description 转入用户登录界面或主页
     * @param request Http请求
     * @return 系统登录界面
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null){
            return "redirect:/";
        }
        return "login";
    }

    /**
     * @Description
     * @param phone 手机号
     * @param password 密码
     * @param request Http请求
     * @return 系统登录界面
     */
    @PostMapping("/login/verification")
    public String verification(@RequestParam("phone") String phone,
                               @RequestParam("password") String password,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = loginService.login(phone, password);
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        session.setAttribute("user", null);
        loginService.logout(phone);
        return "redirect:/";
    }
    /**
     * @Description 提供普通用户注册以及律师用户注册两种不同的通道
     * @return 相应的用户注册界面
     */
    @GetMapping("/register")
    public String register(){
        return "register/index";
    }

    /**
     * @Description 普通用户注册信息收集
     * @return 信息收集界面
     */
    @GetMapping("/register/normal")
    public String normal(){
        return "register/registerNormal";
    }

    /**
     * @Description 普通用户注册信息收集
     * @param phone 手机号
     * @param name 真实姓名
     * @param password 密码
     * @param idno 身份证号
     * @return 信息收集界面
     */
    @PostMapping("/register/normal/input")
    public String normalInput(@RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("verify") String verify,
                         @RequestParam("idno") String idno){
        if (!password.equals(verify)) {
            throw new LegalsysException(ErrorEnum.VERIFYNOTMATCH);
        }
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setPassword(password);
        user.setIdno(idno);
        loginService.register(phone, name, password, idno);
        return "redirect:/login";
    }

    /**
     * @Description 律师用户的信息收集
     * @return 信息收集界面
     */
    @GetMapping("/register/lawyer")
    public String lawyer(){
        return "register/registerLawyer";
    }

    /**
     * @Description 律师用户的信息收集
     * @param phone 手机号
     * @param name 真实姓名
     * @param password 密码
     * @param idno 身份证号
     * @param licensefile 律师执照
     * @param firmname 律所信息
     * @return 信息收集界面
     */
    @PostMapping("/register/lawyer/input")
    public String normal(@RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("verify") String verify,
                         @RequestParam("idno") String idno,
                         @RequestParam("firmname") String firmname,
                         @RequestParam("licensefile") Part licensefile,
                         HttpServletRequest request){
        if (!password.equals(verify)) {
            throw new LegalsysException(ErrorEnum.VERIFYNOTMATCH);
        }
        try {
            String filename = licensefile.getSubmittedFileName();
            String path = request.getSession().getServletContext().getRealPath(fileLocation);
            String lincenseurl = request.getScheme() + "://" + request.getServerName() + fileLocation + filename;

            User user = new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            user.setIdno(idno);
            user.setLicenseurl(lincenseurl);
            user.setFirmname(firmname);
            loginService.registerLawyer(phone, name, password, idno, lincenseurl, firmname);
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

}
