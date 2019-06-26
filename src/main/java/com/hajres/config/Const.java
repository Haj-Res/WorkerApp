package com.hajres.config;

public abstract class Const {
    // Pagination configuration constants
    public static final String DEFAULT_FIRST_PAGE_STRING = "1";
    public static final String DEFAULT_PAGE_SIZE_STRING = "10";
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 20;

    // Parameter and entity attribute names used by the controllers
    public static final String PAGE_PARAM_NAME = "page";
    public static final String PAGE_SIZE_PARAM_NAME = "pageSize";
    public static final String PAGE_COUNT_PARAM_NAME = "pageCount";

    public static final String FILTER_PARAM_NAME = "filter";

    public static final String ROUTE_WORKER = "/worker";
    public static final String ROUTE_COMPANY = "/company";
    public static final String ROUTE_LIST = "/list";
    public static final String ROUTE_EDIT = "/edit";
    public static final String ROUTE_ADD = "/add";
    public static final String ROUTE_SAVE = "/save";
    public static final String ROUTE_DELETE = "/delete";

}
