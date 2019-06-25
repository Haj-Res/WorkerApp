package com.hajres.controller;

import com.hajres.domain.entity.User;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.service.RestNewsService;
import com.hajres.news.service.RestNewsServiceImpl;
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
    // Change the first number to affect how many minutes the caching lasts
    private static final int CACHING_DURATION = 5*60*1000;

    @Autowired
    private RestNewsService restNewsService;
    private List<ArticleSource> sourceList;
    private long sourceTimestamp;

    @GetMapping("")
    public String getBreakingNews(@RequestParam(value = "source", required = false) String source,
                                  Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryPreference() == null ? "us" : user.getCountryPreference().getCountryId();
        long minuteAgo = System.currentTimeMillis() - CACHING_DURATION;
        boolean isOlderThanFiveMinutes = sourceTimestamp < minuteAgo;
        if (this.sourceList == null || isOlderThanFiveMinutes) {
            this.sourceList = restNewsService.getArticleSourcesByCountry(countryCode);
            this.sourceTimestamp = System.currentTimeMillis();
            System.out.println("Getting sources.");
        }
        List<ArticleSource> sources = this.sourceList;
        List<Article> articles;
        Map<String, String> paramMap = new HashMap<>();
        if (source == null) {
            paramMap.put(RestNewsServiceImpl.PARAM_COUNTRY, countryCode);
            articles = restNewsService.getBreakingNews(paramMap);
        } else {
            paramMap.put(RestNewsServiceImpl.PARAM_SOURCES, source);
            articles = restNewsService.getBreakingNews(paramMap);
        }
        model.addAttribute("articles", articles);
        model.addAttribute("sources", sources);
        return "news/article-list";
    }


}
