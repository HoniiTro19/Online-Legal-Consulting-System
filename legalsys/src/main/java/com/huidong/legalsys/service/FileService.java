package com.huidong.legalsys.service;

import com.huidong.legalsys.configuration.FileConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

@Service
public class FileService{

    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileConfiguration conf){
        this.fileStorageLocation= Paths.get(conf.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String storeFile(MultipartFile file) throws IOException{
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String oriname=file.getOriginalFilename();
        String[] parts=oriname.split("\\.");
        String fileName= StringUtils.cleanPath(df.format(new Date()))+"."+parts[parts.length-1];
        Path targetLocation=this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return targetLocation.toString();
    }
}
