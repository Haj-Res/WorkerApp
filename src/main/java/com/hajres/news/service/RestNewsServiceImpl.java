package com.hajres.news.service;

import com.hajres.news.model.Article;
import com.hajres.news.model.NewsApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestNewsServiceImpl implements RestNewsService {
    // Base url parts
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String TOP_HEADLINES = "top-headlines?";
    private static final String EVERYTHING = "everything";

    // Api key
    private static final String API_KEY = "apiKey=05a74fc1fd934e96916010f9c80559e7";

    // Parameter names
    private static final String query = "q";
    private static final String PARAMETER_COUNTRY = "country=";
    private static final String PARAMETER_SOURCE = "sources=";
    private static final String PARAMETER_CATEGORY = "category=";


    @Override
    public List<Article> getBreakingNewsByCountry(String countryCode) {
        RestTemplate restTemplate = new RestTemplate();

        String sourceUrl = BASE_URL + TOP_HEADLINES + PARAMETER_COUNTRY + countryCode + "&" + API_KEY;
        System.out.println(sourceUrl);
        ResponseEntity<NewsApiResponse> responseEntity = restTemplate.getForEntity(sourceUrl, NewsApiResponse.class);
        NewsApiResponse response  = responseEntity.getBody();
        if(response != null && response.getStatus().equals("ok")) {
            return response.getArticles();
        } else {
            return null;
        }
    }
}
