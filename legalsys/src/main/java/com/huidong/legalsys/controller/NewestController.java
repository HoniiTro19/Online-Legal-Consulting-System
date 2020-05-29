package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Article;
import com.huidong.legalsys.domain.Category;
import com.huidong.legalsys.service.ArticleService;
import com.huidong.legalsys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author  高谦
 * 最新文章列表页面
 */
@Controller
public class NewestController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/newest")
    public String newest(Map<String,Object> map){
        ArrayList<Category> categories=(ArrayList<Category>)categoryService.getCategoriesLimits(8);
        map.put("categories",categories);
        ArrayList<Article> newestArticles=(ArrayList<Article>) articleService.getArticlesByDate(1000);
        map.put("newestArticles",newestArticles);
        return "newest";
    }
}
