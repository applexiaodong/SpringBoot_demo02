package com.dong.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description 上传文件工具类
 * @author: wangxd
 * @create: 2019-08-29
 **/
public class FileUtil {

    public static Boolean uploadFile(byte[] file,String fileName,String filePath) throws IOException {
        FileOutputStream fos = null;
        try {
            File targetFile = new File(filePath);
            //目录不存在，创建目录
            if (!targetFile.exists()){
                targetFile.mkdirs();
            }
            fos = new FileOutputStream(filePath+fileName);
            fos.write(file);
            fos.flush();
            //写入成功
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            fos.close();
        }
    }
}
