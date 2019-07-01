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

    void deleteCountry(String id);

    // NewsCategory

    NewsCategory getCategoryById(String id);

    List<NewsCategory> getCategoryList();

    Map<String, String> getCategories();

    void saveCategory(NewsCategory category);

    void deleteCategory(String id);

    // Language
    Language getLanguageById(String id);

    List<Language> getLanguageList();

    Map<String, String> getLanguages();

    void saveLanguage(Language language);

    void deleteLanguage(String id);

    // SortOrder
    SortOrder getSortOrder(String id);

    List<SortOrder> getSortOrderList();

    Map<String, String> getSortOrders();

    void saveSortOrder(SortOrder sortOrder);

    void deleteSortOrder(String id);

}
