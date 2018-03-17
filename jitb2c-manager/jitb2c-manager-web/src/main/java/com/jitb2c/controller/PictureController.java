package com.jitb2c.controller;

import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传图片处理
 * @Author wuqiong6
 * @Date 2018/3/17 21:02
 */
@Controller
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换为json格式的字符串
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}
