package com.jitb2c.search.pojo;

import java.util.List;

/**
 * 搜索结果封装
 * @Author wuqiong6
 * @Date 2018/3/28 8:43
 */
public class SearchResult {

    //商品列表
    private List<Item> itemList;
    //总记录数
    private long recordCount;
    //总页数
    private long pageCount;
    //当前页
    private long curPage;

    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    public long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
    public long getPageCount() {
        return pageCount;
    }
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
    public long getCurPage() {
        return curPage;
    }
    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
