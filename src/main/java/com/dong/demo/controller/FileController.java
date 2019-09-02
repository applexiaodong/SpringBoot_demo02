package com.dong.demo.controller;



import com.dong.demo.domain.ResultMessage;
import com.dong.demo.enums.ResultEnum;
import com.dong.demo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${uploadFile.location}")
    private String location;

    @RequestMapping(value = "/uploadView")
    public String uploadFile(){
        return "file/uploadView";
    }

    /** 
    * @Desc: 单文件上传 
    * @Author: wangxd 
    * @Date: 2019/8/31 
    */ 
    @RequestMapping(value = "/upload",method=RequestMethod.POST)
    @ResponseBody
    public ResultMessage upload(@RequestParam(value = "file",required = true) MultipartFile file){
        //上传到服务器
        if (file.isEmpty()){
            return ResultMessage.error(ResultEnum.FILE_NOT_EMPTY);
        }
        //原始文件名
        String originalFileName = file.getOriginalFilename();
        //文件后缀
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        //防止重名，重命名
        String newFileName = UUID.randomUUID()+"."+suffix;

        try {
            Boolean flag = FileUtil.uploadFile(file.getBytes(),newFileName,location);
            if (!flag){
                return ResultMessage.error(ResultEnum.FILE_UPLOAD_FAIL);
            }
            String fullFilePath = "/upload/" + newFileName;
            logger.info("上传路径：{}",fullFilePath);
            return ResultMessage.success(fullFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultMessage.error(ResultEnum.FILE_UPLOAD_FAIL);
        }
    }

    /**
    * @Desc: 多文件上传
    * @Author: wangxd 
    * @Date: 2019/8/31 
    */ 
    @RequestMapping(value = "/multiUpload",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage multiUpload( @RequestParam(value = "file",required = true) MultipartFile[] files){

        List<String> picUrl = new ArrayList<>();
        logger.info("文件数量:{}",files.length);
        //上传到服务器
        if (files.length==0){
            return ResultMessage.error(ResultEnum.FILE_NOT_EMPTY);
        }

        for (MultipartFile file : files){
            //原始文件名
            String originalFileName = file.getOriginalFilename();
            //文件后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            //防止重名，重命名
            String newFileName = UUID.randomUUID()+"."+suffix;

            try {
                Boolean flag = FileUtil.uploadFile(file.getBytes(),newFileName,location);
                if (!flag){
                    return ResultMessage.error(ResultEnum.FILE_UPLOAD_FAIL);
                }
                String fullFilePath = "/upload/" + newFileName;
                logger.info("上传路径：{}",fullFilePath);
                picUrl.add(fullFilePath);

            } catch (IOException e) {
                e.printStackTrace();
                return ResultMessage.error(ResultEnum.FILE_UPLOAD_FAIL);
            }
        }
        return ResultMessage.success(picUrl);
    }

}
