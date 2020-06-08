package com.huidong.legalsys.domain;

/**
 * 讨论区咨询表单
 * id 讨论区咨询编号
 * phone 发布咨询用户的手机号
 * title 咨询的标题
 * content 咨询的内容
 * status 咨询的状态
 *  @see com.huidong.legalsys.enumeration.SessionStatusEnum
 */

public class Session {
    private Integer id;
    private String phone;
    private String title;
    private String content;
    private Integer status;

    @Override
    public String toString(){
        return "Session{" +
                "phone=" + phone +
                ",title=" + title +
                ",content=" + content +
                ",status=" + status + "}";
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public Integer getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
