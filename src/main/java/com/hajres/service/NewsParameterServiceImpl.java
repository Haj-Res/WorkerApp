package com.hajres.service;

import com.hajres.domain.dao.NewsDAO;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsParameterServiceImpl implements NewsParameterService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    @Transactional
    public Country getCountryById(String id) {
        return newsDAO.findCountryById(id);
    }

    @Override
    @Transactional
    public List<Country> getCountries() {
        return newsDAO.findAllCountries();
    }

    @Override
    @Transactional
    public void saveCountry(Country country) {
        newsDAO.saveCountry(country);
    }

    @Override
    public void updateCountry(Country country, String oldId) {
        newsDAO.updateCountry(country, oldId);
    }

    @Override
    @Transactional
    public void deleteCountry(String id) {
        newsDAO.deleteCountry(id);
    }

    @Override
    @Transactional
    public NewsCategory getNewsCategoryById(String id) {
        return newsDAO.findCategoryById(id);
    }

    @Override
    @Transactional
    public List<NewsCategory> getNewsCategory() {
        return newsDAO.findAllCategories();
    }

    @Override
    @Transactional
    public Map<String, String> getNewsCategoryMap() {
        List<NewsCategory> list = newsDAO.findAllCategories();
        Map<String, String> categories = new HashMap<>();
        list.forEach(l -> categories.put(l.getId(), l.getName()));
        return categories;
    }

    @Override
    @Transactional
    public void saveNewsCategory(NewsCategory category) {
        newsDAO.saveCategory(category);

    }

    @Override
    public void updateNewsCategory(NewsCategory category, String oldId) {
        newsDAO.updateNewsCategory(category, oldId);
    }

    @Override
    @Transactional
    public void deleteNewsCategory(String id) {
        newsDAO.deleteCategory(id);

    }

    @Override
    @Transactional
    public Language getLanguageById(String id) {
        return newsDAO.findLanguageById(id);
    }

    @Override
    @Transactional
    public List<Language> getLanguageList() {
        return newsDAO.findAllLanguages();
    }

    @Override
    @Transactional
    public Map<String, String> getLanguages() {
        List<Language> list = newsDAO.findAllLanguages();
        Map<String, String> languages = new HashMap<>();
        list.forEach(l -> languages.put(l.getId(), l.getName()));
        return languages;
    }

    @Override
    @Transactional
    public void saveLanguage(Language language) {
        newsDAO.saveLanguage(language);
    }

    @Override
    public void updateLanguage(Language language, String oldId) {
        newsDAO.updateLanguage(language, oldId);
    }

    @Override
    @Transactional
    public void deleteLanguage(String id) {
        newsDAO.deleteLanguage(id);
    }

    @Override
    @Transactional
    public SortOrder getSortOrder(String id) {
        return newsDAO.findSortOrderById(id);
    }

    @Override
    @Transactional
    public List<SortOrder> getSortOrderList() {
        return newsDAO.findAllSortOrders();
    }

    @Override
    @Transactional
    public Map<String, String> getSortOrders() {
        List<SortOrder> list = newsDAO.findAllSortOrders();
        Map<String, String> sortOrders = new HashMap<>();
        list.forEach(l -> sortOrders.put(l.getId(), l.getName()));
        return sortOrders;
    }

    @Override
    @Transactional
    public void saveSortOrder(SortOrder sortOrder) {
        newsDAO.saveSortOrder(sortOrder);
    }

    @Override
    @Transactional
    public void updateSortOrder(SortOrder sortOrder, String oldId) {
        newsDAO.updateSortOrder(sortOrder, oldId);
    }

    @Override
    @Transactional
    public void deleteSortOrder(String id) {
        newsDAO.deleteSortOrder(id);
    }
}
