package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ManageService;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户管理的控制层
 */
@Controller
public class ManageController {

    @Autowired
    private ManageService manageService;
    @Autowired
    private ConsultService consultService;

    /**
     * @Description 展示注册用户个人信息
     * @param request Http请求
     * @param map 前后端数据传输
     * @return 用户管理界面
     */
    @GetMapping("/manage")
    public String manage(HttpServletRequest request,
                       Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        map.put("user", user);
        return "manage/index";
    }

    /**
     * @Description 用户修改登录密码
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     * @param request Http请求
     * @return 返回用户管理界面
     */
    @PostMapping("/manage/changePassword")
    public String changePassword(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword") String newpassword,
                                 HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.changePassword(phone, oldpassword, newpassword);
        return "manage/changePassword";
    }

    /**
     * @Description 用户修改律师认证信息
     * @param licenseurl 律师执照地址
     * @param firmname 律所信息
     * @param request Http请求
     * @return 返回用户管理界面
     */
    @PostMapping("/manage/lawyerAuth")
    public String lawyerAuth(@RequestParam("licenseurl") String licenseurl,
                             @RequestParam("firmname") String firmname,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.lawyerAuth(phone, licenseurl, firmname);
        return "manage/lawyerAuth";
    }


    @GetMapping("/manage/consult")
    public String consult(HttpServletRequest request,
                              Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        List<Consult> consults = manageService.getConsultsByPhone(phone);
        map.put("consults", consults);
        return "manage/consult";
    }

    @GetMapping("/manage/consult/detail")
    public String consultDetail(@RequestParam("id") Integer id,
                                Map<String, Object> map) {
        Consult consult = consultService.getConsult(id);
        map.put("consult", consult);
        return "consult/detail";
    }

    @PostMapping("/manage/deleteConsult")
    public String deleteConsult(@RequestParam("consultid") Integer consultid){
        manageService.deleteConsult(consultid);
        return "redirect:/manage/consult";
    }

    @GetMapping("/manage/convr")
    public String convrUser(HttpServletRequest request,
                              Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        List<Convr> convrs = manageService.getConvrs(phone);
        map.put("convrs", convrs);
        return "manage/convr";
    }

    @PostMapping("/manage/deleteConvr")
    public String deleteConvr(@RequestParam("convrid") Integer convrid){
        manageService.deleteConvr(convrid);
        return "redirect:/manage/convr";
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
        return "manage/contConvr";
    }
}
