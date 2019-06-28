package com.hajres.domain.dao;

import com.hajres.news.model.Language;
import com.hajres.news.model.NewsCategory;
import com.hajres.news.model.SortOrder;

import java.util.List;
import java.util.Map;

public interface NewsDTO {

    Map<String, String> findAllLanguages();

    List<String> findAllCategories();

    Map<String, String> findAllSortOrders();

    Language saveLanguage(Language language);

    NewsCategory saveCategory(NewsCategory category);

    SortOrder saveSortOrder(SortOrder order);

}
