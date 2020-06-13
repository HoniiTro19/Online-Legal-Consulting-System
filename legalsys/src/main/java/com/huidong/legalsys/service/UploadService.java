package com.huidong.legalsys.service;

import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.enumeration.ErrorEnum;
import com.huidong.legalsys.exception.LegalsysException;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    @Value("${spring.servlet.multipart.location}")
    private String fileLocation;

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    public String LicenseUpload(MultipartFile licensefile, MultipartHttpServletRequest request, String phone) {
        String lincenseurl = "";
        if (!licensefile.isEmpty()) {
            try {
                String filename = licensefile.getOriginalFilename();
                String suffixname = filename.substring(filename.lastIndexOf("."));
                lincenseurl = fileLocation + phone + suffixname;
                File dest = new File(lincenseurl);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                licensefile.transferTo(dest);
            } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                logger.error("file upload fail!");
            }
        return lincenseurl;
        }
    }
