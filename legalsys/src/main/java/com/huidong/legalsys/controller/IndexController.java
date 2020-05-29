package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Article;
import com.huidong.legalsys.domain.Category;
import com.huidong.legalsys.service.ArticleService;
import com.huidong.legalsys.service.CategoryService;
import com.huidong.legalsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 高谦
 * 首页文章显示 controller
 */
@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String index(Map<String,Object> map){
        // 查找图片轮播文章
        ArrayList<Article> carouses=(ArrayList<Article>) articleService.getAllCarouselArticles();
        map.put("carouses",carouses);

        // 查找最新文章
        ArrayList<Article> articlesByDate=(ArrayList<Article>) articleService.getArticlesByDate(6);
        map.put("newest",articlesByDate);

        // 查找访问量最大的文章
        ArrayList<Article> articlesByVisitor=(ArrayList<Article>) articleService.getArticleByVisitor(6);
        map.put("popular",articlesByVisitor);
        // 全部专栏
        ArrayList<Category> categoriesLimits=(ArrayList<Category>) categoryService.getCategoriesLimits(6);
        map.put("categories",categoriesLimits);

        return "index";
    }
}
