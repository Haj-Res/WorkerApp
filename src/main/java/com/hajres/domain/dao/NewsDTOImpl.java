package com.hajres.domain.dao;

import com.hajres.domain.entity.news.CachedRecord;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDTOImpl implements NewsDTO {

    private final SessionFactory factory;

    @Autowired
    public NewsDTOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Language> findAllLanguages() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Language ", Language.class).getResultList();
    }

    @Override
    public List<NewsCategory> findAllCategories() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from NewsCategory ", NewsCategory.class).getResultList();
    }

    @Override
    public List<SortOrder> findAllSortOrders() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from SortOrder ", SortOrder.class).getResultList();
    }

    @Override
    public List<Country> findAllCountries() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Country ", Country.class).getResultList();
    }

    @Override
    public Language saveLanguage(Language language) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(language);
        return language;
    }

    @Override
    public NewsCategory saveCategory(NewsCategory category) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(category);
        return category;
    }

    @Override
    public SortOrder saveSortOrder(SortOrder order) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(order);
        return order;
    }

    @Override
    public Country saveCountry(Country country) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(country);
        return country;
    }

    @Override
    public Language findLanguageById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(Language.class, id);
    }

    @Override
    public NewsCategory findCategoryById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(NewsCategory.class, id);
    }

    @Override
    public Country findCountryById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(Country.class, id);
    }

    @Override
    public CachedRecord findCachedRecord(String url) {
        Session session = factory.getCurrentSession();
        try {
            return session.createQuery("from CachedRecord where url=:url", CachedRecord.class).setParameter("url", url).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CachedRecord saveCachedRecord(CachedRecord record) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate((record));
        return record;
    }

    @Override
    public SortOrder findSortOrderById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(SortOrder.class, id);
    }

    @Override
    public void deleteSortOrder(String id) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from SortOrder where id = :id").setParameter("id", id).executeUpdate();
    }
}
