package com.hajres.news;

public abstract class News {
    // Base url parts
    public static final String URL_TOP_HEADLINES = "https://newsapi.org/v2/top-headlines?";
    public static final String URL_EVERYTHING = "https://newsapi.org/v2/everything?";
    public static final String URL_SOURCES = "https://newsapi.org/v2/sources?";

    // Parameter names
    public static final String PARAM_QUERY = "q";
    public static final String PARAM_COUNTRY = "country";
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_SOURCES = "sources";

    // News sources caching duration
    public static final int CACHING_DURATION = 5*60*1000;

    // Api key
    public static final String API_KEY = "apiKey=05a74fc1fd934e96916010f9c80559e7";}
