package com.hajres.controller;

import com.hajres.domain.entity.User;
import com.hajres.news.model.Article;
import com.hajres.news.service.RestNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    RestNewsService restNewsService;

    @GetMapping("")
    public String getBreakingNews(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String countryCode  = user.getCountryPreference() == null ? "us" : user.getCountryPreference().getCountryId();
        List<Article> articles = restNewsService.getBreakingNewsByCountry(user.getCountryPreference().getCountryId());
        model.addAttribute("articles", articles);
        return "news/article-list";
    }
}
