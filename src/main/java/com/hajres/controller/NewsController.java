package com.hajres.controller;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.User;
import com.hajres.news.News;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.service.RestNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final RestNewsService restNewsService;
    private Map<String, String> categories;
    private Map<String, String> languages;
    private Map<String, String> sortOrders;
    private List<Country> countries;

    private List<Article> articles;

    @Autowired
    public NewsController(RestNewsService restNewsService) {
        this.restNewsService = restNewsService;
        this.categories = restNewsService.getCategories();
        this.languages = restNewsService.getLanguages();
        this.sortOrders = restNewsService.getSortOrders();
        this.countries = restNewsService.getCountries();
    }

    @GetMapping("")
    public String getBreakingNews(@RequestParam(value = "source", required = false) String source,
                                  @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) String page,
                                  @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) String pageSize,
                                  Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryIdOrNull() == null ? "us" : user.getCountryIdOrNull();

        List<ArticleSource> sources = restNewsService.getArticleSourcesByCountry(countryCode);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(Const.PAGE_PARAM_NAME, page);
        paramMap.put(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        PaginatedResult<Article> paginatedResult;

        String additionalParameters = "";
        if (source == null || source.isEmpty()) {
            paramMap.put(News.PARAM_COUNTRY, countryCode);
            if (user.getCategoryPreference() != null) {
                paramMap.put(News.PARAM_CATEGORY, user.getCategoryPreference().getId());
            }
            paginatedResult = restNewsService.getNews(News.URL_TOP_HEADLINES, paramMap);
        } else {
            paramMap.put(News.PARAM_SOURCES, source);
            paginatedResult = restNewsService.getNews(News.URL_EVERYTHING, paramMap);
            additionalParameters += News.PARAM_SOURCES + "=" + source + "&";

        }

        Map<String, String> countries = new HashMap<>();
        this.countries.forEach(c -> {
            String name = c.getInternationalName() + " (" + c.getLocalName() + ")";
            countries.put(c.getCountryId(), name);
        });
        this.articles = paginatedResult.getResultList();

        EditUserDto dto = new EditUserDto();

        dto.setCountry(user.getCountryIdOrNull());
        dto.setCategory(user.getCategoryIdOrNull());

        model.addAttribute("dto", dto);

        prepareModel(model, paginatedResult.getPageCount(), page, pageSize);

        model.addAttribute("countries", countries);
        model.addAttribute("additionalParameters", additionalParameters);
        model.addAttribute("sources", sources);
        return "news/personal-feed";
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
        model.addAttribute("categories", this.categories);
        model.addAttribute("languages", this.languages);
        model.addAttribute("sortMap", this.sortOrders);

        return "news/article";
    }

    @GetMapping("/category/{category}")
    public String showByCategory(@PathVariable String category,
                                 @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) String page,
                                 @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) String pageSize,
                                 HttpServletRequest request,
                                 Model model) {

        User user = (User) request.getSession().getAttribute("user");
        String countryCode = user.getCountryIdOrNull() == null ? "us" : user.getCountryIdOrNull();

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(News.PARAM_COUNTRY, countryCode);
        paramMap.put(News.PARAM_CATEGORY, category);
        paramMap.put(Const.PAGE_PARAM_NAME, page);
        paramMap.put(Const.PAGE_SIZE_PARAM_NAME, pageSize);

        PaginatedResult<Article> paginatedResult = restNewsService.getNews(News.URL_TOP_HEADLINES, paramMap);
        this.articles = paginatedResult.getResultList();

        prepareModel(model, paginatedResult.getPageCount(), page, pageSize);

        return "news/article-list";
    }

    @GetMapping("/search")
    public String showSearchResults(@RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) String page,
                                    @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) String pageSize,
                                    @RequestParam(value = "q", required = false, defaultValue = "") String query,
                                    @RequestParam(value = "language", required = false, defaultValue = "") String language,
                                    @RequestParam(value = "sortBy", required = false, defaultValue = "publishedAt") String sortBy,
                                    Model model) throws UnsupportedEncodingException {
        String additionalParameters = "";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(Const.PAGE_PARAM_NAME, page);
        paramMap.put(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        if (!query.isEmpty()) {
            query = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            paramMap.put(News.PARAM_QUERY, query);
            additionalParameters += News.PARAM_QUERY + "=" + query + "&";
        }

        if (!language.equals("")) {
            paramMap.put(News.PARAM_LANGUAGE, language);
        }
        paramMap.put(News.PARAM_SORT_BY, sortBy);
        additionalParameters += News.PARAM_SORT_BY + "=" + sortBy + "&";
        PaginatedResult<Article> paginatedResult = restNewsService.getNews(News.URL_EVERYTHING, paramMap);
        this.articles = paginatedResult.getResultList();
        prepareModel(model, paginatedResult.getPageCount(), page, pageSize);
        model.addAttribute("additionalParameters", additionalParameters);

        return "news/search-results";

    }


    private void prepareModel(Model model, long pageCount, String page, String pageSize) {
        model.addAttribute("articles", this.articles);
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, pageCount);
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        model.addAttribute("categories", this.categories);
        model.addAttribute("languages", this.languages);
        model.addAttribute("sortMap", this.sortOrders);
    }
}
