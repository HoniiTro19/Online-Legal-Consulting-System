package com.huidong.legalsys.controller;

import com.huidong.legalsys.domain.Article;
import com.huidong.legalsys.domain.User;
import com.huidong.legalsys.service.ArticleService;
import com.huidong.legalsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @author 高谦
 */
@Controller
public class AboutController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @GetMapping("/about")
    public String about(Map<String,Object> map){
        User user=userService.getDefaultUser();
        if(user.getResume()==null){
            Article blank=new Article();
            blank.setContent("当前没有介绍文章页面或者页面被删除，请进入后台维护简历页面！");
            map.put("article",blank);
            return "about";
        }
        Article article=articleService.getArticleById(user.getResume());
        if(article==null){
            Article blank=new Article();
            blank.setContent("当前没有介绍文章页面或者页面被删除，请进入后台维护简历页面！");
            map.put("article",blank);
        }else{
            map.put("article",article);
        }
        return "about";
    }
}
