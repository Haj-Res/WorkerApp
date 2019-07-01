package com.hajres.service;

import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import java.util.List;
import java.util.Map;

public interface NewsParameterService {

    // Country
    Country getCountryById(String id);

    List<Country> getCountries();

    void saveCountry(Country country);

    void updateCountry(Country country, String oldId);

    void deleteCountry(String id);

    // NewsCategory

    NewsCategory getNewsCategoryById(String id);

    List<NewsCategory> getNewsCategory();

    Map<String, String> getNewsCategoryMap();

    void saveNewsCategory(NewsCategory category);

    void updateNewsCategory(NewsCategory category, String oldId);

    void deleteNewsCategory(String id);

    // Language
    Language getLanguageById(String id);

    List<Language> getLanguageList();

    Map<String, String> getLanguages();

    void saveLanguage(Language language);

    void updateLanguage(Language language, String oldId);

    void deleteLanguage(String id);

    // SortOrder
    SortOrder getSortOrder(String id);

    List<SortOrder> getSortOrderList();

    Map<String, String> getSortOrders();

    void saveSortOrder(SortOrder sortOrder);

    void updateSortOrder(SortOrder sortOrder, String oldId);

    void deleteSortOrder(String id);

}
