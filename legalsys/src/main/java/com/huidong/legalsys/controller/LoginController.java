package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.RegisterTypeEnum;
import com.huidong.legalsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password,
                        HttpServletRequest request){
        HttpSession session = request.getSession();
        try {
            User user = loginService.login(phone, password);
            session.setAttribute("user", user);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@RequestParam("type") Integer type){
        if (type.equals(RegisterTypeEnum.NORMAL.getType())){
            return "redirect:/register/normal";
        }else {
            return "redirect:/register/lawyer";
        }
    }

    @PostMapping("/register/normal")
    public String normal(@RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("idno") String idno){
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setPassword(password);
        user.setIdno(idno);
        loginService.register(phone, name, password, idno);
        return "redirect:/login";
    }

    @PostMapping("/register/lawyer")
    public String normal(@RequestParam("phone") String phone,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password,
                         @RequestParam("idno") String idno,
                         @RequestParam("licenseurl") String lincenseurl,
                         @RequestParam("firmname") String firmname){
        User user = new User();
        user.setPhone(phone);
        user.setName(name);
        user.setPassword(password);
        user.setIdno(idno);
        user.setLicenseurl(lincenseurl);
        user.setFirmname(firmname);
        loginService.registerLawyer(phone, name, password, idno, lincenseurl, firmname);
        return "redirect:/login";
    }

}
