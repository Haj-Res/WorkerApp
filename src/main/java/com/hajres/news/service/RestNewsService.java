package com.hajres.news.service;

import com.hajres.PaginatedResult;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;

import java.util.List;
import java.util.Map;

public interface RestNewsService {

    PaginatedResult<Article> getNews(String newsType, Map<String, String> paramMap);


    List<ArticleSource> getArticleSourcesByCountry(String countryCode);

}
