package com.example.demo.common;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    // 此方法可以清空文件夹
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + File.separator + tempList[i]);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }
    // D:\Coding\IDEAjavacoding\springboot-blog\target\classes\static\img\
    // D:\Coding\IDEAjavacoding\springboot-blog\avatar\
    public  static  final String UPLOADS_PATH = "D:\\Coding\\IDEAjavacoding\\springboot-blog\\avatar\\";
    public static String uploads(MultipartFile photo) throws IOException {
        // 文件大小已经在也yml中配置，基本不会超过限制
        // 文件后缀，如jpg...
        final String fileSuffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf('.')+1);

        // 文件名
        String filename = UUID.randomUUID().toString().replace("_","")+"."+ fileSuffix;

        // 文件写入
        File descFile = new File(UPLOADS_PATH,filename);
        photo.transferTo(descFile);

        // 文件URL
        String url = "/avatar/" + filename;

        // 将文件URL存储进数据库中，这里在controller层实现

        return url;
    }

}

