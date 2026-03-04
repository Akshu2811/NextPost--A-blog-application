package com.scaler.blogapp.articles;

import com.scaler.blogapp.users.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
/*
    private ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }
*/
    @GetMapping("")
    public String getArticles(){
        return "get all articles";
    }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable("id") String id){
        return "get article by id" + id;
    }

    @PostMapping("")
    public String createArticle(@AuthenticationPrincipal UserEntity user){
        return "create article called by" + user.getUsername();
    }
}
