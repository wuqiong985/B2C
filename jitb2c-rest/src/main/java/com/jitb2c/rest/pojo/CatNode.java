package com.jitb2c.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述商品分类节点Node的类
 * @Author wuqiong6
 * @Date 2018/3/25 15:06
 */
public class CatNode {

    /**
     * 节点名 相当于节点数据结构中的n
     * 注释指的是转换为json对象时的key值，不指明则默认属性名
     */
    @JsonProperty("n")
    private String name;

    /**
     * url 相当于节点数据结构中的u
     */
    @JsonProperty("u")
    private String url;

    /**
     * 值 相当于节点数据结构中的i 可能是CatNode型，也可能是String
     */
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
