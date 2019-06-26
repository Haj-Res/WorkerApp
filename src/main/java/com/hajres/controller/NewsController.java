package com.hajres.controller;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.entity.Country;
import com.hajres.domain.entity.User;
import com.hajres.news.News;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.service.RestNewsService;
import com.hajres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UserService userService;

    private List<Article> articles;
    @Autowired
    public NewsController(RestNewsService restNewsService, @Qualifier("userServiceImpl") UserService userService) {
        this.restNewsService = restNewsService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getBreakingNews(@RequestParam(value = "source", required = false) String source,
                                  @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) String page,
                                  @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) String pageSize,
                                  Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryPreference() == null ? "us" : user.getCountryPreference().getCountryId();

        List<ArticleSource> sources = restNewsService.getArticleSourcesByCountry(countryCode);
        PaginatedResult<Article> articlePaginatedResult;

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(Const.PAGE_PARAM_NAME, page);
        paramMap.put(Const.PAGE_SIZE_PARAM_NAME, pageSize);

        if (source == null || source.isEmpty()) {
            paramMap.put(News.PARAM_COUNTRY, countryCode);
            if (user.getCategoryPreference() != null) {
                paramMap.put(News.PARAM_CATEGORY, user.getCategoryPreference());
            }
            articlePaginatedResult = restNewsService.getBreakingNews(paramMap);
        } else {
            paramMap.put(News.PARAM_SOURCES, source);
            articlePaginatedResult = restNewsService.getBreakingNews(paramMap);
        }
        List<Country> countryList = userService.findAllCountries();

        Map<String, String> countries = new HashMap<>();
        countryList.forEach(c -> {
            String name = c.getInternationalName() + " (" + c.getLocalName() + ")";
            countries.put(c.getCountryId(), name);
        });
        this.articles = articlePaginatedResult.getResultList();
        model.addAttribute("articles", this.articles);

        EditUserDto dto = new EditUserDto();
        dto.setCountry(user.getCountryPreference().getCountryId());
        dto.setCategory(user.getCategoryPreference());
        model.addAttribute("dto", dto);
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, articlePaginatedResult.getPageCount());
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        model.addAttribute("countries", countries);
        model.addAttribute("categories", News.CATEGORIES);
        model.addAttribute("selectedSource", source);
        model.addAttribute("sources", sources);
        return "news/article-list";
    }

    @GetMapping("/articles/{hash}")
    public String showArticle(@PathVariable int hash,
                              Model model) {
        Article article = null;
        for (Article value : this.articles) {
            if (value.hashCode() == hash) {
                article = value;
                break;
            }
        }
        if (article == null) {
            return "redirect: news";
        }
        model.addAttribute("article", article);
        model.addAttribute("categories", News.CATEGORIES);
        return "news/article";
    }


}
