package com.hajres.controller;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
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
                                  @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) String page,
                                  @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) String pageSize,
                                  Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryPreference() == null ? "us" : user.getCountryPreference().getCountryId();

        List<ArticleSource> sources = restNewsService.getArticleSourcesByCountry(countryCode);
        PaginatedResult<Article> articles;

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(Const.PAGE_PARAM_NAME, page);
        paramMap.put(Const.PAGE_SIZE_PARAM_NAME, pageSize);

        if (source == null || source.isEmpty()) {
            paramMap.put(News.PARAM_COUNTRY, countryCode);
            if (user.getCategoryPreference() != null) {
                paramMap.put(News.PARAM_CATEGORY, user.getCategoryPreference());
            }
            articles = restNewsService.getBreakingNews(paramMap);
        } else {
            paramMap.put(News.PARAM_SOURCES, source);
            articles = restNewsService.getBreakingNews(paramMap);
        }
        model.addAttribute("articles", articles.getResultList());
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, articles.getPageCount());
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        model.addAttribute("selectedSource", source);
        model.addAttribute("sources", sources);
        return "news/article-list";
    }


}
