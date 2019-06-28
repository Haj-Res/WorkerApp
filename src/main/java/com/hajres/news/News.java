package com.hajres.news;

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
    public static final int CACHING_DURATION_MINUTES = 5;
}
