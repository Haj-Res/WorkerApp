package com.hajres.controller;

import com.hajres.domain.entity.User;
import com.hajres.news.News;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.service.RestNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final RestNewsService restNewsService;

    @Autowired
    public NewsController(RestNewsService restNewsService) {
        this.restNewsService = restNewsService;
    }

    @GetMapping("")
    public String getBreakingNews(@RequestParam(value = "source", required = false) String source,
                                  Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryPreference() == null ? "us" : user.getCountryPreference().getCountryId();
        List<ArticleSource> sources = restNewsService.getArticleSourcesByCountry(countryCode);
        List<Article> articles;
        Map<String, String> paramMap = new HashMap<>();
        if (source == null || source.isEmpty()) {
            paramMap.put(News.PARAM_COUNTRY, countryCode);
            articles = restNewsService.getBreakingNews(paramMap);
        } else {
            paramMap.put(News.PARAM_SOURCES, source);
            articles = restNewsService.getBreakingNews(paramMap);
        }
        model.addAttribute("articles", articles);
        model.addAttribute("sources", sources);
        return "news/article-list";
    }


}
