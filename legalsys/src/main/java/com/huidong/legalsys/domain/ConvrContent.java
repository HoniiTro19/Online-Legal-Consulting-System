package com.huidong.legalsys.domain;

public class ConvrContent {
    private String phone;
    private String content;
    private String time;

    @Override
    public String toString() {
        return "ConvrContent{" +
                "phone=" + phone +
                ",content=" + content +
                ",time=" + time + "}";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
