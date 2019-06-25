package com.hajres.news.service;

import com.hajres.news.model.Article;

import java.util.List;

public interface RestNewsService {
    List<Article> getBreakingNews();
}
