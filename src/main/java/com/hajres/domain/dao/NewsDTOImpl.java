package com.hajres.domain.dao;

import com.hajres.news.model.Language;
import com.hajres.news.model.NewsCategory;
import com.hajres.news.model.SortOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsDTOImpl implements NewsDTO {

    private final SessionFactory factory;

    @Autowired
    public NewsDTOImpl(SessionFactory factory) {
        this.factory = factory;
    }
    @Override
    public Map<String, String> findAllLanguages() {
        Session session =factory.getCurrentSession();
        List<Language> list = session.createQuery("from Language ", Language.class).getResultList();
        Map<String, String> languages = new HashMap<>();
        list.forEach(l -> {
            languages.put(l.getId(), l.getName());
        });
        return languages;
    }

    @Override
    public List<String> findAllCategories() {
        Session session = factory.getCurrentSession();
        return session.createQuery("select name from NewsCategory ", String.class).getResultList();
    }

    @Override
    public Map<String, String> findAllSortOrders() {
        Session session = factory.getCurrentSession();
        List<SortOrder> list = session.createQuery("from SortOrder ", SortOrder.class).getResultList();
        Map<String, String> sortOrders = new HashMap<>();
        list.forEach(l-> {
            sortOrders.put(l.getId(), l.getName());

        });
        return sortOrders;
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
}
