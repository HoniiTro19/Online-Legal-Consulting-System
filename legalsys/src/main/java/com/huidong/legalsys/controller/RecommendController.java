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
 * @autor 高谦
 * 推荐文章相关前端接口
 */
@Controller
public class RecommendController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    /**
     * 获取推荐文章的列表页面controller
     * @param map 前端参数传递
     * @return
     */
    @GetMapping("/recommend")
    public String recommend(Map<String,Object> map){
        ArrayList<Category> categories=(ArrayList<Category>) categoryService.getCategoriesLimits(8);
        map.put("categories",categories);
        ArrayList<Article> recommandArticleas=(ArrayList<Article>) articleService.getArticleByVisitor(100);
        map.put("recommandArticleas",recommandArticleas);
        return "recommend";
    }
}
