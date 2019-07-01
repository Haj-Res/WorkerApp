package com.hajres.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.domain.dao.NewsDTO;
import com.hajres.domain.entity.news.CachedRecord;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;
import com.hajres.news.News;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.model.NewsApiResponse;
import com.hajres.news.model.SourceList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestNewsServiceImpl implements RestNewsService {

    @Autowired
    private NewsDTO newsDTO;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    @Transactional
    public PaginatedResult<Article> getNews(String newsType, Map<String, String> paramMap) {
        String targetUrl = buildUrl(newsType, paramMap);

        CachedRecord record = newsDTO.findCachedRecord(targetUrl);

        // if cached record is null or too old, fetch new data
        if (record == null || record.isOutdated(News.CACHING_DURATION_MINUTES)) {
            if (record == null) {
                logger.info("No records found for request " + targetUrl);
                record = new CachedRecord();
            } else {
                logger.info("Outdated records for " + targetUrl);
            }
            // Fetching new data
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ResponseEntity<NewsApiResponse> responseEntity;
            try {
                responseEntity = restTemplate.getForEntity(targetUrl, NewsApiResponse.class);
            } catch (Exception ex) {
                return null;
            }
            NewsApiResponse response = responseEntity.getBody();

            // mapping response to CachedRecord and saving it to DB
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writer().writeValueAsString(response);
                record.setJson(json);
                record.setUrl(targetUrl);
                record.setTimestamp(Instant.now());
                newsDTO.saveCachedRecord(record);
            } catch (JsonProcessingException e) {
                logger.error(e.getLocalizedMessage());
            }
            // parsing response and sending it back
            int pageSize = Integer.parseInt(paramMap.get(Const.PAGE_PARAM_NAME));
            return parseResponseForArticles(response, pageSize);
        } else {
            // record exists and isn't too old, fetch and return result
            logger.info("Fetching cached records for request " + targetUrl);
            ObjectMapper mapper = new ObjectMapper();
            NewsApiResponse response;
            try {
                response = mapper.readValue(record.getJson(), NewsApiResponse.class);
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                return null;
            }
            int pageSize = Integer.parseInt(paramMap.get(Const.PAGE_PARAM_NAME));
            return parseResponseForArticles(response, pageSize);
        }
    }

    private PaginatedResult<Article> parseResponseForArticles(NewsApiResponse response, int pageSize) {
        if (response != null && response.getStatus().equals("ok")) {
            PaginatedResult<Article> result = new PaginatedResult<>();
            result.setResultList(response.getArticles());

            int pageCount = (response.getTotalResults() - 1) / pageSize + 1;
            result.setPageCount(pageCount);

            return result;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public List<ArticleSource> getArticleSourcesByCountry(String countryCode) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(News.PARAM_COUNTRY, countryCode);
        String targetUrl = buildUrl(News.URL_SOURCES, paramMap);

        CachedRecord record = newsDTO.findCachedRecord(targetUrl);
        if (record == null || record.isOutdated(News.CACHING_DURATION_MINUTES)) {
            if (record == null) {
                logger.info("No records found for request " + targetUrl);
                record = new CachedRecord();
            } else {
                logger.info("Outdated records for " + targetUrl);
            }

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ResponseEntity<SourceList> responseEntity = restTemplate.getForEntity(targetUrl, SourceList.class);
            SourceList sourceList = responseEntity.getBody();

            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writer().writeValueAsString(sourceList);
                record.setUrl(targetUrl);
                record.setJson(json);
                record.setTimestamp(Instant.now());
                newsDTO.saveCachedRecord(record);
            } catch (JsonProcessingException e) {
                logger.error(e.getLocalizedMessage());
            }
            return sourceList == null ? null : sourceList.getSources();
        } else {
            logger.info("Fetching cached records for request " + targetUrl);
            ObjectMapper mapper = new ObjectMapper();
            SourceList sourceList;
            try {
                sourceList = mapper.readValue(record.getJson(), SourceList.class);
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                return null;
            }
            return sourceList.getSources();
        }
    }

    @Override
    @Transactional
    public Map<String, String> getCategories() {
        List<NewsCategory> list = newsDTO.findAllCategories();
        Map<String, String> categories = new HashMap<>();
        list.forEach(l -> {
            categories.put(l.getId(), l.getName());
        });
        return categories;
    }

    @Override
    @Transactional
    public List<Country> getCountries() {
        return newsDTO.findAllCountries();
    }

    @Override
    @Transactional
    public Map<String, String> getLanguages() {
        List<Language> list = newsDTO.findAllLanguages();
        Map<String, String> languages = new HashMap<>();
        list.forEach(l -> {
            languages.put(l.getId(), l.getName());
        });
        return languages;
    }

    @Override
    @Transactional
    public Map<String, String> getSortOrders() {
        List<SortOrder> list = newsDTO.findAllSortOrders();
        Map<String, String> sortOrders = new HashMap<>();
        list.forEach(l -> {
            sortOrders.put(l.getId(), l.getName());

        });
        return sortOrders;
    }

    @Override
    @Transactional
    public List<SortOrder> getSortOrderList() {
        return newsDTO.findAllSortOrders();
    }

    @Override
    @Transactional
    public List<Language> getLanguageList() {
        return newsDTO.findAllLanguages();
    }

    @Override
    @Transactional
    public List<NewsCategory> getCategoryList() {
        return newsDTO.findAllCategories();
    }

    @Override
    @Transactional
    public SortOrder getSortOrder(String id) {
        return newsDTO.findSortOrderById(id);
    }

    @Override
    @Transactional
    public void saveSortOrder(SortOrder sortOrder) {
        logger.info("Saving sortOrder " + sortOrder);
        newsDTO.saveSortOrder(sortOrder);
    }

    @Override
    @Transactional
    public void deleteSortOrder(String id) {
        logger.info("Deleting sort order '" + id + "'");
        newsDTO.deleteSortOrder(id);
    }

    private String buildUrl(String base, Map<String, String> paramMap) {
        String url = base;
        for (String key : paramMap.keySet()) {
            url = url.concat(key + "=" + paramMap.get(key) + "&");
        }
        url = url.concat(News.API_KEY);
        logger.info("Requesting articles from " + url);
        return url;
    }
}
