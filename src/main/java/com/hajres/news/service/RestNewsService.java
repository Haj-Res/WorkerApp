package com.hajres.news.service;

import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

import java.util.List;
import java.util.Map;

public interface RestNewsService {

    List<Article> getBreakingNews(Map<String, String> paramMap);

    List<ArticleSource> getArticleSourcesByCountry(String countryCode);

}
