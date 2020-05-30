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

public class Stature {
    private Integer lawid;
    private Integer part;
    private Integer chapter;
    private Integer article;
    private String title;
    private String content;
    private Integer clickrate;

    @Override
    public String toString(){
        return "Stature{" +
                "lawid=" + lawid +
                ",part=" + part +
                ",chapter=" + chapter +
                ",article=" + article +
                ",title=" + title +
                ",content=" + content +
                ",clickrate=" + clickrate + "}";
    }

    public Integer getLawid() {
        return lawid;
    }

    public void setLawid(Integer lawid) {
        this.lawid = lawid;
    }

    public Integer getPart() {
        return part;
    }

    public void setPart(Integer part) {
        this.part = part;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClickrate() {
        return clickrate;
    }

    public void setClickrate(Integer clickrate) {
        this.clickrate = clickrate;
    }
}
