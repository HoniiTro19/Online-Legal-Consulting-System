package com.huidong.legalsys.domain;

/**
 * 用户咨询表单
 * id 用户咨询编号
 * phone 用户的手机号
 * query 咨询的内容
 * type 咨询的类型
 *  @see com.huidong.legalsys.enumeration.ConsultTypeEnum
 * result 咨询的结果
 * time 咨询的时间
 */

public class Consult {
    private Integer id;
    private String phone;
    private String title;
    private String query;
    private Integer type;
    private String result;
    private String time;

    @Override
    public String toString(){
        return "Consult{" +
                "id=" + id +
                ",phone=" + phone +
                ",title=" + title +
                ",query=" + query +
                ",type=" + type +
                ",result=" + result +
                ",time=" + time + "}";
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {return id;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {return phone;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {return title;}

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {return query;}

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {return type;}

    public String getResult() {return result;}

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {return time;}

    public void setTime(String time) {
        this.time = time;
    }
}
