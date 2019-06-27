package com.hajres.news.service;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.news.News;
import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.model.NewsApiResponse;
import com.hajres.news.model.SourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestNewsServiceImpl implements RestNewsService {

    // Properties use to decide if you send the cached source list or to fetch it from the web
    private List<ArticleSource> sourceList;
    private long sourceTimestamp;
    private String sourceCountry;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public PaginatedResult<Article> getNews(String newsType, Map<String, String> paramMap) {
        String targetUrl = buildUrl(newsType, paramMap);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        ResponseEntity<NewsApiResponse> responseEntity;
        try {
            responseEntity = restTemplate.getForEntity(targetUrl, NewsApiResponse.class);
        } catch (Exception ex) {
            return new PaginatedResult<>();
        }

        NewsApiResponse response = responseEntity.getBody();

        if (response != null && response.getStatus().equals("ok")) {
            PaginatedResult<Article> result = new PaginatedResult<>();
            result.setResultList(response.getArticles());

            int pageSize = Integer.parseInt(paramMap.get(Const.PAGE_PARAM_NAME));
            int pageCount = (response.getTotalResults() - 1) / pageSize + 1;
            result.setPageCount(pageCount);

            return result;
        } else {
            return null;
        }
    }

    @Override
    public List<ArticleSource> getArticleSourcesByCountry(String countryCode) {

        long minuteAgo = System.currentTimeMillis() - News.CACHING_DURATION;
        boolean olderThanCacheDuration = sourceTimestamp < minuteAgo;
        if (this.sourceList != null && !olderThanCacheDuration && this.sourceCountry.equals(countryCode)) {
            logger.info("Returning cached source list.");
            return this.sourceList;
        }

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(News.PARAM_COUNTRY, countryCode);
        String targetUrl = buildUrl(News.URL_SOURCES, paramMap);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        ResponseEntity<SourceList> responseEntity = restTemplate.getForEntity(targetUrl, SourceList.class);
        SourceList sourceList = responseEntity.getBody();

        this.sourceTimestamp = System.currentTimeMillis();
        this.sourceCountry = countryCode;
        this.sourceList = sourceList == null ? null : sourceList.getSources();

        return this.sourceList;
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
