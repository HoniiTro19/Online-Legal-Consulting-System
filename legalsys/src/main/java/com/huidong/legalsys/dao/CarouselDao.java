package com.huidong.legalsys.dao;
import com.huidong.legalsys.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  高谦
 * 博客轮播dao层实现
 */


@Repository
public interface  CarouselDao {
    // 获取所有轮播文章
    public List<Article> getAllCarousel();

}
