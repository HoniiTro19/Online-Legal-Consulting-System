package com.huidong.legalsys.service;

import com.huidong.legalsys.configuration.FileConfiguration;
import com.huidong.legalsys.handle.ExceptionHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 上传文件的业务逻辑层
 */
@Service
public class FileService{

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    private final Path fileStorageLocation;

    @Value("${config.pattern}")
    private String pattern;
    @Autowired
    public FileService(FileConfiguration conf){
        this.fileStorageLocation = Paths.get(conf.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @Description 存储用户上传的文件
     * @param file 文件
     * @return String 用户上传的文件在本地的地址
     */
    public String storeFile(MultipartFile file){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String oriname = file.getOriginalFilename();
        String[] parts = oriname.split("\\.");
        String fileName = StringUtils.cleanPath(format.format(new Date())) + "." + parts[parts.length-1];
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
        String stringLocation = targetLocation.toString();
        logger.info("上传新的律师执照{}", stringLocation);
        return stringLocation;
    }
}
