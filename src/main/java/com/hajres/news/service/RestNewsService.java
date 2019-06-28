package com.hajres.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hajres.PaginatedResult;
import com.hajres.domain.entity.news.Country;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RestNewsService {

    PaginatedResult<Article> getNews(String newsType, Map<String, String> paramMap);

    List<ArticleSource> getArticleSourcesByCountry(String countryCode);

    Map<String, String> getCategories();

    List<Country> getCountries();

    Map<String, String> getLanguages();

    Map<String, String> getSortOrders();

}
