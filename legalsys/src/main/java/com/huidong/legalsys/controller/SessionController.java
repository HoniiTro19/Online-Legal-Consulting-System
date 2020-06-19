package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Session;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ManageService;
import com.huidong.legalsys.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 讨论区会话的控制层
 */
@Controller
public class SessionController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private ManageService manageService;

    /**
     * @Description 用户进入讨论区，可以看到其他用户提出的问题，可以查看问题的详细情况，也可以自己提出问题
     * @param map 前后端传递数据
     * @return 讨论区主界面
     */
    @GetMapping("/session")
    public String sessions(Map<String, Object> map){
        List<Session> sessions = sessionService.getAllSessions();
        map.put("sessions", sessions);
        return "session/index";
    }

    /**
     * @Description 查看讨论区咨询的详细内容
     * @param id 咨询的编号
     * @param map 前后端传递数据
     * @return 展示讨论区咨询的详细内容的界面
     */
    @GetMapping("/session/detail")
    public String sessionDetail(@RequestParam("id") Integer id,
                           Map<String, Object> map){
        Session sessionInfo = sessionService.getSession(id);
        map.put("sessionInfo", sessionInfo);
        return "session/detail";
    }

    /**
     * @Description 创建新的咨询
     * @return 创建新咨询的表单
     */
    @GetMapping("/session/newsession")
    public String newsession(){
        return "session/newsession";
    }

    /**
     * @Description 提交新的咨询
     * @param title 咨询的标题
     * @param content 咨询的内容
     * @param request http请求
     * @return 讨论区界面
     */
    @PostMapping("/session/newsession/upload")
    public String addsession(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        sessionService.newsession(phone, title, content);
        return "redirect:/session";
    }

    /**
     * @Description 删除讨论区咨询，建立相应的会话
     * @param sessionid 咨询编号
     * @param request http请求
     * @return 讨论区界面
     */
    @GetMapping("/session/estbConvrLawyer")
    public String estbconvrLawyer(@RequestParam("sessionid") Integer sessionid,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String lawyerphone = user.getPhone();
        sessionService.estbConvrLawyer(lawyerphone, sessionid);
        return "redirect:/session";
    }

    /**
     * @Description 普通用户主动建立的会话
     * @param lawyerphone 律师手机号
     * @param request http请求
     * @return 讨论区界面
     */
    @GetMapping("/session/estbConvr")
    public String estbconvr(@RequestParam("lawyerphone") String lawyerphone,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        sessionService.estbConvr(lawyerphone, user);
        return "redirect:/manage/userConvrs";
    }

    /**
     * @Description 返回所有民事诉讼类型
     * @param map 前后端传递参数
     * @return 所有民事诉讼类型的界面
     */
    @GetMapping("/session/lawyerMap")
    public String lawyerMap(Map<String, Object> map) {
        ArrayList<String> allCategories = sessionService.getAllCategories();
        map.put("categories", allCategories);
        return "/session/allCategories";
    }

    /**
     * @Description 返回某个类别的所有律师信息
     * @param category 民事诉讼的类型
     * @param map 前后端传递参数
     * @return 该类别下所有律师的信息
     */
    @GetMapping("/session/lawyerByCategories")
    public String lawyerByCategories(@RequestParam("category") String category,
                                     Map<String, Object> map) {
        ArrayList<User> lawyerByCategory = sessionService.getlawyersByCategory(category);
        map.put("lawyerByCategory", lawyerByCategory);
        map.put("category", category);
        return "session/lawyerByCategory";
    }

    /**
     * @Description 获得律师的详细信息
     * @param phone 律师手机号
     * @param map 前后端传递参数
     * @return 律师详情界面
     */
    @GetMapping("/session/lawyerInfo")
    public String lawyersInfo(@RequestParam("phone") String phone,
                              Map<String, Object> map) {
        User lawyerInfo = manageService.getUserInfo(phone);
        map.put("lawyerInfo", lawyerInfo);
        return "session/lawyerInfo";
    }
}
