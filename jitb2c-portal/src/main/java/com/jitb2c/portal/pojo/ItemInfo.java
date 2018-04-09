package com.jitb2c.portal.pojo;

import com.jitb2c.pojo.TbItem;

/**
 * @Author wuqiong6
 * @Date 2018/4/8 17:25
 */
public class ItemInfo extends TbItem {

    /**
     * 防止image是多张图片叠加成的字符串，转换为数组，然后前台取第一个
     * @return
     */
    public String[] getImages(){
        String image = getImage();
        if (image != null){
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
