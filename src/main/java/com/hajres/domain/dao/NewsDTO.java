package com.hajres.domain.dao;

import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import java.util.List;
import java.util.Map;

public interface NewsDTO {

    Map<String, String> findAllLanguages();

    List<String> findAllCategories();

    Map<String, String> findAllSortOrders();

    Language saveLanguage(Language language);

    NewsCategory saveCategory(NewsCategory category);

    SortOrder saveSortOrder(SortOrder order);

    Language findLanguageById(String id);

    NewsCategory findCategoryById(String id);
}
