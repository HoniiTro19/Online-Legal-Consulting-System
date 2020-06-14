package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Consult;
import com.huidong.legalsys.domain.Convr;
import com.huidong.legalsys.domain.ConvrContent;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.service.ManageService;
import com.huidong.legalsys.service.ConsultService;

import com.huidong.legalsys.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UploadService uploadService;
    @Value("${config.adminphone}")
    private String adminPhone;
    @Value("${config.pattern}")
    private String pattern;

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
        String phone = userInfo.getPhone();
        Boolean islawyer = manageService.isLawyer(phone);
        map.put("userInfo", userInfo);
        if (userInfo.getPhone().equals(adminPhone)) {
            return "manage/indexAdmin";
        }else if (islawyer == false){
            return "manage/indexNormal";
        }else {
            return "manage/indexLawyer";
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
     * @param licensefile 律师执照
     * @param firmname 律所信息
     * @param request Http请求
     * @return 用户个人信息界面
     */
    @PostMapping("/manage/lawyerAuth/upload")
    public String lawyerAuthUpload(@RequestParam("firmname") String firmname,
                                   @RequestParam("licensefile") MultipartFile licensefile,
                                   MultipartHttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String phone = user.getPhone();
        String licenseurl = uploadService.LicenseUpload(licensefile, request, phone);
        user.setLicenseurl(licenseurl);
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
     * @Description 查看会话记录的详情
     * @param id 会话记录的编号
     * @param map 前后端传递数据
     * @return 会话记录的详情的界面
     */
    @GetMapping("/manage/userConvrs/detail")
    public String convrDetail(@RequestParam("id") Integer id,
                              Map<String, Object> map,
                              HttpServletRequest request) {
        Convr convrInfo = consultService.getConvr(id);
        ArrayList<ConvrContent> recordconvrs = new ArrayList<>();
        String time = convrInfo.getTime();
        String convr = convrInfo.getConvr();
        String[] times = time.split("\\\n");
        String[] convrs = convr.split("\\\n");
        for (int i=0; i<convrs.length; ++i) {
            String singleTime = times[i];
            String singleContent = convrs[i];
            String[] singleContents = singleContent.split("\\\t");
            ConvrContent convrContent = new ConvrContent();
            convrContent.setPhone(singleContents[0]);
            convrContent.setContent(singleContents[1]);
            convrContent.setTime(singleTime);
            recordconvrs.add(convrContent);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        session.setAttribute("convrid", convrInfo.getId());
        String myphone = user.getPhone();
        if (myphone.equals(convrInfo.getPhone())) {
            map.put("hisphone", convrInfo.getLawyerphone());
        }else {
            map.put("hisphone", convrInfo.getPhone());
        }
        map.put("myphone", myphone);
        map.put("recordconvrs", recordconvrs);
        map.put("adminphone", adminPhone);
        return "manage/convrDetail";
    }

    /**
     * @Description 用户继续在自己的会话中聊天
     * @param record 新增聊天内容
     * @param request http请求
     * @return 更新后的会话记录详情界面
     */
    @GetMapping("/manage/userConvrs/detail/upload")
    public String contConvr(@RequestParam("record") String record,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer convrid = (Integer) session.getAttribute("convrid");
        session.removeAttribute("convrid");
        String phone = user.getPhone();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        String recordtime = format.format(date);
        manageService.contConvr(phone, record, recordtime, convrid);
        return "redirect:/manage/userConvrs/detail?id=" + convrid;
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

    @GetMapping("/manage/allUsers/getLicense")
    public ResponseEntity<byte[]> getLicense(@RequestParam("licenseurl") String licenseurl) throws Exception{
        byte[] licensebyte;
        licensebyte = fileToByte(new File(licenseurl));
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(licensebyte, headers, HttpStatus.OK);
    }

    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
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
