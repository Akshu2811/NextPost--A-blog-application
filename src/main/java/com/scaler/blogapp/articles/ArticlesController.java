package com.scaler.blogapp.articles;

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
        return "articles";
    }
}
