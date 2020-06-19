package com.huidong.legalsys.domain;

/**
 * 法律纠纷类型的表单
 * id 法律纠纷类型编号
 * category 法律纠纷类型名称
 */
public class Dispute {
    private Integer id;
    private String category;

    @Override
    public String toString() {
        return "Dispute{" +
                "id=" + id +
                ",category=" + category + "}";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
