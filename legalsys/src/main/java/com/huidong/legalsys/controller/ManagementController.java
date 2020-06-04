package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ManagementController {

    @Autowired
    private ManageService manageService;

    @GetMapping("/manage")
    public User manage(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }

    @PostMapping("/manage/changePassword")
    public String changePassword(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword") String newpassword,
                                 HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.changePassword(phone, oldpassword, newpassword);
        return "redirect:/manage";
    }

    @PostMapping("/manage/lawyerAuth")
    public String lawyerAuth(@RequestParam("licenseurl") String licenseurl,
                             @RequestParam("firmname") String firmname,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.lawyerAuth(phone, licenseurl, firmname);
        return "redirect:/manage";
    }

    @GetMapping("/manage/consultUser")
    public String consultUser(HttpServletRequest request,
                              Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        List<Consult> consults = manageService.getConsultsByPhone(phone);
        map.put("consults", consults);
        return "/manage/consultUser";
    }

    @PostMapping("/manage/deleteConsult")
    public String deleteConsult(@RequestParam("consultid") Integer consultid){
        manageService.deleteConsult(consultid);
        return "redirect:/manage/consultUser";
    }

    @GetMapping("/manage/convrUser")
    public String convrUser(HttpServletRequest request,
                              Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        List<Convr> convrs = manageService.getConvrs(phone);
        map.put("convrs", convrs);
        return "/manage/convrUser";
    }

    @PostMapping("/manage/deleteConvr")
    public String deleteConvr(@RequestParam("convrid") Integer convrid){
        manageService.deleteConvr(convrid);
        return "redirect:/manage/convrUser";
    }

    @PostMapping("/manage/contConvr")
    public String contConvr(@RequestParam("record") String record,
                            @RequestParam("recordtime") String recordtime,
                            @RequestParam("convrid") Integer convrid,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.contConvr(phone, record, recordtime, convrid);
        return "redirect:/manage/convrUser";
    }
}
