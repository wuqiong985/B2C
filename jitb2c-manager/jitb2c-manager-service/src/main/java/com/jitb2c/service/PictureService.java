package com.jitb2c.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传图片的接口
 * @Author wuqiong6
 * @Date 2018/3/17 19:58
 */
public interface PictureService {

    /**
     * 上传图片
     * @param uploadFile 需要上传的文件对象
     * @return
     */
    Map uploadPicture(MultipartFile uploadFile);
}
