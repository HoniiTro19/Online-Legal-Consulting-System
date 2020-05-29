package com.huidong.legalsys.domain;

import lombok.Data;

/*
 * 法律条文表单
 * lawid 法条在法条库中的编号
 * part 法条的"编"信息
 * chapter 法条的"章"信息
 * article 法条的"条"信息
 * title 法条的标题
 * content 法条的内容
 * clickrate 法条的点击数
 */

@Data
public class Stature {
    private Integer lawid;
    private Integer part;
    private Integer chapter;
    private Integer article;
    private String title;
    private String content;
    private Integer clickrate;
}
