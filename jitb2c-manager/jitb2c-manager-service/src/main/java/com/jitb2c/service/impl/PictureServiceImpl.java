package com.jitb2c.service.impl;

import com.jitb2c.common.utils.FtpUtil;
import com.jitb2c.common.utils.IDUtils;
import com.jitb2c.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 图片操作的实现类
 * @Author wuqiong6
 * @Date 2018/3/17 20:00
 */
@Service
public class PictureServiceImpl implements PictureService {

    //通过Spring自带的方法直接读取配置文件
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;

    @Value("${FTP_PORT}")
    private Integer FTP_PORT;

    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;

    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;



    /**
     * 上传图片的实现方法
     * @param uploadFile 需要上传的文件对象
     * @return
     */
    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap();
        try {
        //1.生成一个新的文件名
        //1.1取原始文件名（带扩展名）
        String oldName = uploadFile.getOriginalFilename();
        //1.2生成新的文件名
//        UUID.randomUUID();
        String newName = IDUtils.genImageName();
        newName = newName + oldName.substring(oldName.lastIndexOf("."));

        //2.图片上传
        String imagePath = new DateTime().toString("/yyyy/MM/dd");
        boolean result =  FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD, FTP_BASE_PATH
                            ,imagePath,newName,uploadFile.getInputStream());

        //返回结果
        if (!result){
            resultMap.put("error",1);
            resultMap.put("message","文件上传失败");
            return resultMap;
        }
        resultMap.put("error",0);
        resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
        return  resultMap;
        } catch (IOException e) {
            resultMap.put("error",1);
            resultMap.put("message","文件上传发生异常");
            return resultMap;
        }
    }
}
