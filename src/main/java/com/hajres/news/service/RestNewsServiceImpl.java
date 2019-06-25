package com.hajres.news.service;

import com.hajres.news.model.Article;
import com.hajres.news.model.ArticleSource;
import com.hajres.news.model.NewsApiResponse;
import com.hajres.news.model.SourceList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestNewsServiceImpl implements RestNewsService {
    // Base url parts
    public static final String URL_TOP_HEADLINES = "https://newsapi.org/v2/top-headlines?";
    public static final String URL_EVERYTHING = "https://newsapi.org/v2/everything?";
    public static final String URL_SOURCES = "https://newsapi.org/v2/sources?";

    // Parameter names
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_COUNTRY = "country";
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_SOURCES = "sources";

    // Api key
    private static final String API_KEY = "apiKey=05a74fc1fd934e96916010f9c80559e7";

    private List<ArticleSource> sources;

    @Override
    public List<Article> getBreakingNews(Map<String, String> paramMap) {
        String targetUrl = buildUrl(URL_TOP_HEADLINES, paramMap);
        System.out.println("Requesting " + targetUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NewsApiResponse> responseEntity = restTemplate.getForEntity(targetUrl, NewsApiResponse.class);
        NewsApiResponse response = responseEntity.getBody();
        if (response != null && response.getStatus().equals("ok")) {
            return response.getArticles();
        } else {
            return null;
        }
    }


    @Override
    public List<ArticleSource> getArticleSourcesByCountry(String countryCode) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(PARAM_COUNTRY, countryCode);
        String targetUrl = buildUrl(URL_SOURCES, paramMap);
        System.out.println("Requesting " + targetUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SourceList> responseEntity = restTemplate.getForEntity(targetUrl, SourceList.class);
        SourceList sourceList = responseEntity.getBody();
        return sourceList == null ? null : sourceList.getSources();
    }

    private String buildUrl(String base, Map<String, String> paramMap) {
        String url = base;
        for (String key : paramMap.keySet()) {
            url = url.concat(key + "=" + paramMap.get(key) + "&");
        }
        url = url.concat(API_KEY);
        return url;
    }
}
