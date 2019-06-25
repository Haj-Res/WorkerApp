package com.hajres.controller;

import com.hajres.news.model.Article;
import com.hajres.news.service.RestNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    RestNewsService restNewsService;

    @GetMapping("")
    public String getBreakingNews(Model model) {
        List<Article> articles = restNewsService.getBreakingNews();
        model.addAttribute("articles", articles);
        return "news/article-list";
    }
}
