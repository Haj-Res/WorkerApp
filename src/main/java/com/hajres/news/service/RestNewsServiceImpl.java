package com.hajres.news.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hajres.news.model.Article;
import com.hajres.news.model.NewsApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    public String getNewsTest() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = String.format("%s/content/search/aspects/v1?%s", BASE_URL, API_KEY);
        System.out.println(fooResourceUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseEntity.getBody());
        JsonNode notification = root.path("requestUrl");
        String result = notification.asText();
        return responseEntity.getBody();
    }

    @Override
    public List<Article> getBreakingNews() {
        RestTemplate restTemplate = new RestTemplate();

        String sourceUrl = BASE_URL + TOP_HEADLINES + PARAMETER_COUNTRY + "us&" + API_KEY;
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
