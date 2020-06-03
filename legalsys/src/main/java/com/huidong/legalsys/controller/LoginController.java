package com.huidong.legalsys.controller;

import com.huidong.legalsys.enumeration.RegisterTypeEnum;
import com.huidong.legalsys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password){
        try {
            loginService.login(phone, password);
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
}
