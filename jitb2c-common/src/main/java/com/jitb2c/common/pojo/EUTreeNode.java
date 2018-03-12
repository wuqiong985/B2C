package com.jitb2c.common.pojo;

/**封装EasyUI的异步树tree的一个节点的对象
 * Created by wuqiong6 on 2018/3/12.
 */
public class EUTreeNode {

    private long id;

    private String text;

    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
