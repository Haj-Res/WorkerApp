package com.hajres.service;

import com.hajres.domain.dao.NewsDAO;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(getClass().getName());

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
        logger.info("Saving country " + country);
        newsDAO.saveCountry(country);
    }

    @Override
    @Transactional
    public void updateCountry(Country country, String oldId) {
        logger.info("Updating ID for country with old id " + oldId);
        newsDAO.updateCountry(country, oldId);
    }

    @Override
    @Transactional
    public void deleteCountry(String id) {
        logger.info("Deleting country with id " + id);
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
        logger.info("Saving category " + category);
        newsDAO.saveCategory(category);

    }

    @Override
    @Transactional
    public void updateNewsCategory(NewsCategory category, String oldId) {
        logger.info("Updating ID for category with old id " + oldId);
        newsDAO.updateNewsCategory(category, oldId);
    }

    @Override
    @Transactional
    public void deleteNewsCategory(String id) {
        logger.info("Deleting category with id " + id);
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
        logger.info("Saving language " + language);
        newsDAO.saveLanguage(language);
    }

    @Override
    @Transactional
    public void updateLanguage(Language language, String oldId) {
        logger.info("Updating ID for language with old id " + oldId);
        newsDAO.updateLanguage(language, oldId);
    }

    @Override
    @Transactional
    public void deleteLanguage(String id) {
        logger.info("Deleting language with id " + id);
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
        logger.info("Saving sort order " + sortOrder);
        newsDAO.saveSortOrder(sortOrder);
    }

    @Override
    @Transactional
    public void updateSortOrder(SortOrder sortOrder, String oldId) {
        logger.info("Updating ID for sort order with id " + oldId);
        newsDAO.updateSortOrder(sortOrder, oldId);
    }

    @Override
    @Transactional
    public void deleteSortOrder(String id) {
        logger.info("Deleting sort order with id " + id);
        newsDAO.deleteSortOrder(id);
    }
}
