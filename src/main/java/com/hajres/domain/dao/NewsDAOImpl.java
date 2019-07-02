package com.hajres.domain.dao;

import com.hajres.domain.entity.news.CachedRecord;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private final SessionFactory factory;

    @Autowired
    public NewsDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }


    // Country
    @Override
    public Country findCountryById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(Country.class, id);
    }

    @Override
    public List<Country> findAllCountries() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Country ", Country.class).getResultList();
    }

    @Override
    public Country saveCountry(Country country) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(country);
        return country;
    }

    @Override
    public void updateCountry(Country country, String oldId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("update Country set countryId=:id, localName=:localName, internationalName=:internationalName where countryId=:oldId");
        query.setParameter("id", country.getCountryId());
        query.setParameter("localName", country.getLocalName());
        query.setParameter("internationalName", country.getInternationalName());
        query.setParameter("oldId", oldId);
        query.executeUpdate();
    }

    @Override
    public void deleteCountry(String id) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from Country where countryId = :id").setParameter("id", id).executeUpdate();
    }

    // NewsCategory
    @Override
    public NewsCategory findCategoryById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(NewsCategory.class, id);
    }

    @Override
    public List<NewsCategory> findAllCategories() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from NewsCategory where id != :star ", NewsCategory.class).setParameter("star", "*").getResultList();
    }

    @Override
    public NewsCategory saveCategory(NewsCategory category) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(category);
        return category;
    }

    @Override
    public void updateNewsCategory(NewsCategory category, String oldId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("update NewsCategory set id=:id, name=:name where id=:oldId");
        query.setParameter("id", category.getId());
        query.setParameter("name", category.getName());
        query.setParameter("oldId", oldId);
        query.executeUpdate();
    }

    @Override
    public void deleteCategory(String id) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from NewsCategory where id = :id").setParameter("id", id).executeUpdate();
    }

    // Language
    @Override
    public Language findLanguageById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(Language.class, id);
    }

    @Override
    public List<Language> findAllLanguages() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from Language ", Language.class).getResultList();
    }

    @Override
    public Language saveLanguage(Language language) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(language);
        return language;
    }

    @Override
    public void updateLanguage(Language language, String oldId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("update Language set id=:id, name=:name where id=:oldId");
        query.setParameter("id", language.getId());
        query.setParameter("name", language.getName());
        query.setParameter("oldId", oldId);
        query.executeUpdate();
    }

    @Override
    public void deleteLanguage(String id) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from Language where id = :id").setParameter("id", id).executeUpdate();
    }

    // SortOrder
    @Override
    public SortOrder findSortOrderById(String id) {
        Session session = factory.getCurrentSession();
        return session.get(SortOrder.class, id);
    }

    @Override
    public List<SortOrder> findAllSortOrders() {
        Session session = factory.getCurrentSession();
        return session.createQuery("from SortOrder ", SortOrder.class).getResultList();
    }

    @Override
    public SortOrder saveSortOrder(SortOrder order) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(order);
        return order;
    }

    @Override
    public SortOrder updateSortOrder(SortOrder order, String oldId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("update SortOrder set id=:id, name=:name where id=:oldId");
        query.setParameter("id", order.getId());
        query.setParameter("name", order.getName());
        query.setParameter("oldId", oldId);
        query.executeUpdate();
        return order;
    }

    @Override
    public void deleteSortOrder(String id) {
        Session session = factory.getCurrentSession();
        session.createQuery("delete from SortOrder where id = :id").setParameter("id", id).executeUpdate();
    }

    // CachedRecords
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
}
