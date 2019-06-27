package com.hajres.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class News {
    // Api key
    public static final String API_KEY = "apiKey=05a74fc1fd934e96916010f9c80559e7";

    // Base url parts
    public static final String URL_TOP_HEADLINES = "https://newsapi.org/v2/top-headlines?";
    public static final String URL_EVERYTHING = "https://newsapi.org/v2/everything?";
    public static final String URL_SOURCES = "https://newsapi.org/v2/sources?";

    // Parameter names
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_SORT_BY = "sortBy";
    public static final String PARAM_COUNTRY = "country";
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_SOURCES = "sources";
    public static final String PARAM_LANGUAGE = "language";

    // News sources caching duration
    public static final int CACHING_DURATION = 5 * 60 * 1000;

    public static final List<String> CATEGORIES =
            new ArrayList<>(Arrays.asList("Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"));

    public static final Map<String, String> SORT_BY = new HashMap<String, String>() {
        {
            put("relevancy", "Relevance");
            put("popularity", "Popularity");
            put("publishedAt", "Time of publishing");
        }
    };

    public static final Map<String, String> LANGUAGES = new HashMap<String, String>() {
        {
            put("all", "All");
            put("en", "English");
            put("fr", "French");
            put("de", "German");
        }
    };

}
