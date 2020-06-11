package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Stature;
import com.huidong.legalsys.service.PenallawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static java.lang.Byte.SIZE;

/**
 * @Description 法律条文检索的控制层
 */
@Controller
public class PenallawController {

    @Autowired
    private PenallawService penallawService;
    @Value("${config.penallawPath}")
    private String penallawPath;

    /**
     * @Description 查看刑法内容
     * @param map 前后端传递数据
     * @return 刑法内容展示的界面
     */
    @GetMapping("/penallaw")
    public String penallaw(Map<String, Object> map) {
        List<Stature> statures = penallawService.getAllStatures();
        map.put("statures", statures);
        return "penallaw/index";
    }

    /**
     * @Description 用户点击相关法条，系统展示法条详情
     * @param lawid 法条编号
     * @return 展示法条详情的界面
     */
    @GetMapping("/penallaw/detail")
    public String getlaw(@RequestParam("lawid") Integer lawid,
                         Map<String, Object> map) {
        Stature stature = penallawService.getStature(lawid);
        map.put("stature", stature);
        return "penallaw/detail";
    }

    @GetMapping("/penallaw/download")
    public void download(HttpServletResponse response) {
        File file = new File(penallawPath);
        byte[] bytes = new byte[SIZE];
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.addHeader("Content-Disposition", "attachment;fileName=");
            outputStream = response.getOutputStream();
            int length;
            while ((length = bufferedInputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, length);
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedInputStream != null){
                    bufferedInputStream.close();
                }

                if (outputStream != null){
                    outputStream.close();
                }

                if (fileInputStream != null){
                    fileInputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
