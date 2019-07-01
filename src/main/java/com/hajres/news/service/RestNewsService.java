package com.hajres.news.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;

import java.util.List;
import java.util.Map;

public interface RestNewsService {

    PaginatedResult<Article> getNews(String newsType, Map<String, String> paramMap);

    List<ArticleSource> getArticleSourcesByCountry(String countryCode);

    Map<String, String> getCategories();

    List<Country> getCountries();

    Map<String, String> getLanguages();

    Map<String, String> getSortOrders();

    List<SortOrder> getSortOrderList();

    List<Language> getLanguageList();

    List<NewsCategory> getCategoryList();

    SortOrder getSortOrder(String id);

    void saveSortOrder(SortOrder sortOrder);

    void deleteSortOrder(String id);
}
