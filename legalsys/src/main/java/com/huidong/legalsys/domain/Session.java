package com.huidong.legalsys.domain;

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
