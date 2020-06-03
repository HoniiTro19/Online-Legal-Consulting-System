package com.huidong.legalsys.domain;

public class Consult {
    private Integer id;
    private String phone;
    private String query;
    private Integer type;
    private String result;
    private String time;
    private Integer status;

    @Override
    public String toString(){
        return "ConsultLog{" +
                "id=" + id +
                ",phone=" + phone +
                ",query=" + query +
                ",type=" + type +
                ",result=" + result +
                ",time=" + time +
                ",status=" + status + "}";
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
