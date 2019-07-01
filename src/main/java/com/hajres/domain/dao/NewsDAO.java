package com.hajres.domain.dao;

import com.hajres.domain.entity.news.CachedRecord;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import java.util.List;

public interface NewsDAO {

    // Country
    Country findCountryById(String id);

    List<Country> findAllCountries();

    Country saveCountry(Country country);

    void updateCountry(Country country, String oldId);

    void deleteCountry(String id);

    // NewsCategory
    NewsCategory findCategoryById(String id);

    List<NewsCategory> findAllCategories();

    NewsCategory saveCategory(NewsCategory category);

    void updateNewsCategory(NewsCategory category, String oldId);

    void deleteCategory(String id);


    // Language
    Language findLanguageById(String id);

    List<Language> findAllLanguages();

    Language saveLanguage(Language language);

    void updateLanguage(Language language, String oldId);

    void deleteLanguage(String id);

    // SortOrder
    SortOrder findSortOrderById(String id);

    List<SortOrder> findAllSortOrders();

    SortOrder saveSortOrder(SortOrder order);

    SortOrder updateSortOrder(SortOrder order, String oldId);

    void deleteSortOrder(String id);

    // CachedRecord
    CachedRecord findCachedRecord(String url);

    CachedRecord saveCachedRecord(CachedRecord record);

}
