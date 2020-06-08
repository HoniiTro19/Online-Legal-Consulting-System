package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.service.ManageService;
import com.huidong.legalsys.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.ArrayList;
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
    @Value("${config.adminphone}")
    private String adminPhone;

    /**
     * @Description 展示注册用户个人信息
     * @param request Http请求
     * @param map 前后端数据传输
     * @return 用户/管理员个人信息界面
     */
    @GetMapping("/manage")
    public String manage(HttpServletRequest request,
                       Map<String, Object> map){
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        map.put("userInfo", userInfo);
        if (userInfo.getPhone().equals(adminPhone)) {
            return "manage/indexAdmin";
        }else {
            return "manage/index";
        }

    }

    /**
     * @Description 用户修改登录密码
     * @param request http请求
     * @param map 前后端传递数据
     * @return 修改密码的表单界面
     */
    @GetMapping("/manage/changePassword")
    public String changePassword(HttpServletRequest request,
                                 Map<String, Object> map) {
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        map.put("userInfo", userInfo);
        return "manage/changePassword";
    }

    /**
     * @Description 用户修改登录密码
     * @param oldpassword 旧密码
     * @param newpassword 新密码
     * @param request Http请求
     * @return 用户个人信息界面
     */
    @PostMapping("/manage/changePassword/upload")
    public String changePasswordUpload(@RequestParam("oldpassword") String oldpassword,
                                       @RequestParam("newpassword") String newpassword,
                                       @RequestParam("verify") String verify,
                                       HttpServletRequest request){
        if (!newpassword.equals(verify)) {
            throw new LegalsysException(ErrorEnum.VERIFYNOTMATCH);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.changePassword(phone, oldpassword, newpassword);
        user.setPassword(newpassword);
        return "redirect:/manage";
    }

    /**
     * @Description 用户修改律师认证信息
     * @param request http请求
     * @param map 前后端传递数据
     * @return 修改律师认证的表单界面
     */
    @GetMapping("/manage/lawyerAuth")
    public String lawyerAuth(HttpServletRequest request,
                             Map<String, Object> map) {
        HttpSession session = request.getSession();
        User userInfo = (User) session.getAttribute("user");
        map.put("userInfo", userInfo);
        return "manage/lawyerAuth";
    }

    /**
     * @Description 用户修改律师认证信息
     * @param licenseurl 律师执照地址
     * @param firmname 律所信息
     * @param request Http请求
     * @return 用户个人信息界面
     */
    @PostMapping("/manage/lawyerAuth/upload")
    public String lawyerAuthUpload(@RequestParam("licenseurl") String licenseurl,
                             @RequestParam("firmname") String firmname,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.lawyerAuth(phone, licenseurl, firmname);
        return "redirect:/manage";
    }










    /**
     * @Description 用户查看自己的咨询记录
     * @param request http请求
     * @param map 前后端传递数据
     * @return 个人的咨询记录展示界面
     */
    @GetMapping("/manage/userConsults")
    public String userConsults(HttpServletRequest request,
                               Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        ArrayList<Consult> userConsults = manageService.getConsultsByPhone(phone);
        map.put("userConsults", userConsults);
        return "manage/userConsults";
    }

    /**
     * @Description 用户删除咨询记录
     * @param consultid 咨询记录编号
     * @return 个人的咨询记录展示界面
     */
    @GetMapping("/manage/deleteConsult")
    public String deleteConsult(@RequestParam("consultid") Integer consultid){
        manageService.deleteConsult(consultid);
        return "redirect:/manage/userConsults";
    }









    /**
     * @Description 用户查询自己的会话记录
     * @param request http请求
     * @param map 前后端传递数据
     * @return 个人的会话信息展示界面
     */
    @GetMapping("/manage/userConvrs")
    public String userConvrs(HttpServletRequest request,
                              Map<String, Object> map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        ArrayList<Convr> userConvrs = manageService.getConvrs(phone);
        map.put("userConvrs", userConvrs);
        return "manage/userConvrs";
    }

    /**
     * @Description 用户删除会话记录
     * @param convrid 会话记录编号
     * @return 会话记录的详情的界面
     */
    @GetMapping("/manage/deleteConvr")
    public String deleteConvr(@RequestParam("convrid") Integer convrid) {
        manageService.deleteConvr(convrid);
        return "redirect:/manage/userConvrs";
    }

    /**
     * @Description 查看会话记录的详情
     * @param id 会话记录的编号
     * @param map 前后端传递数据
     * @return 会话记录的详情的界面
     */
    @GetMapping("/manage/userConvrs/detail")
    public String convrDetail(@RequestParam("id") Integer id,
                              Map<String, Object> map) {
        Convr convr = consultService.getConvr(id);
        map.put("convr", convr);
        return "manage/convrDetail";
    }

    /**
     * @Description 用户继续在自己的会话中聊天
     * @param record 新增聊天内容
     * @param recordtime 新增聊天内容的时间
     * @param convrid 会话的编号
     * @param request http请求
     * @return 本界面
     */
    @PostMapping("/manage/userConvrs/detail/upload")
    public String contConvr(@RequestParam("record") String record,
                            @RequestParam("recordtime") String recordtime,
                            @RequestParam("convrid") Integer convrid,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        manageService.contConvr(phone, record, recordtime, convrid);
        String location = "/manage/userConvrs/detail?convrid=" + convrid + "/upload";
        return "redirect:" + location;
    }









    /**
     * @Description 获得所有注册用户信息
     * @param map 前后端传递参数
     * @return 注册用户展示界面
     */
    @GetMapping("/manage/allUsers")
    public String allUsers(Map<String, Object> map) {
        ArrayList<User> allUsers = manageService.getAllUsers();
        map.put("allUsers", allUsers);
        return "manage/allUsers";
    }

    /**
     * @Description 获得用户的详细信息
     * @param phone 手机号
     * @param map 前后端传递参数
     * @return 显示用户详细信息的界面
     */
    @GetMapping("/manage/allUsers/detail")
    public String userDetail(@RequestParam("phone") String phone,
                             Map<String, Object> map) {
        User userInfo = manageService.getUserInfo(phone);
        map.put("userInfo", userInfo);
        return "manage/userDetail";
    }

    /**
     * @Description 获得所有法律条文信息
     * @return 展示法律条文信息的界面
     */
    @GetMapping("/manage/allStatures")
    public String allStatures() {
        return "redirect:/penallaw";
    }

    /**
     * @Description 获得所有咨询信息
     * @param map 前后端传递数据
     * @return 展示所有咨询信息的界面
     */
    @GetMapping("/manage/allConsults")
    public String allConsults(Map<String, Object> map) {
        ArrayList<Consult> allConsults = manageService.getAllConsults();
        map.put("userConsults", allConsults);
        return "manage/userConsults";
    }

    /**
     * @Description 获得所有会话信息
     * @param map 前后端传递数据
     * @return 展示所有会话信息的界面
     */
    @GetMapping("/manage/allConvrs")
    public String allConvrs(Map<String, Object> map) {
        ArrayList<Convr> allConvrs = manageService.getAllConvrs();
        map.put("userConvrs", allConvrs);
        return "manage/userConvrs";
    }
}
