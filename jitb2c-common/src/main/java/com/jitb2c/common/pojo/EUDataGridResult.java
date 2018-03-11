package com.jitb2c.common.pojo;


import java.util.List;

/**
 * 封装EasyUI需要的DataGrid对象
 * @Author wuqiong6
 * @Date 2018/3/11 16:55
 */
public class EUDataGridResult {

    /**
     * 记录总数
     */
    private long total;

    /**
     * 数据对象列表
     */
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
