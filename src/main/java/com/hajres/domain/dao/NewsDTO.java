package com.hajres.domain.dao;

import com.hajres.domain.entity.news.CachedRecord;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import java.util.List;
import java.util.Map;

public interface NewsDTO {

    // fetch all
    Map<String, String> findAllLanguages();

    Map<String, String> findAllCategories();

    Map<String, String> findAllSortOrders();

    List<Country> findAllCountries();

    // save or update
    Language saveLanguage(Language language);

    NewsCategory saveCategory(NewsCategory category);

    SortOrder saveSortOrder(SortOrder order);

    Country saveCountry(Country country);

    // fetch one
    Language findLanguageById(String id);

    NewsCategory findCategoryById(String id);

    Country findCountryById(String id);

    CachedRecord findCachedRecord(String url);

    CachedRecord saveCachedRecord(CachedRecord record);
}
