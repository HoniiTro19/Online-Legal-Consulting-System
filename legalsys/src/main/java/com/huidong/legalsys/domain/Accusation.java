package com.huidong.legalsys.domain;

/**
 * 罪名表单
 * id 罪名编号
 * accu 罪名名称
 */
public class Accusation {
    private Integer id;
    private String accu;

    @Override
    public String toString() {
        return "Accusation{" +
                "id=" + id +
                ",accu=" + accu + "}";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccu(String accu) {
        this.accu = accu;
    }
}
