package com.jzheadley.eat.models.services;


import com.jzheadley.eat.models.services.api.CategoryApi;

public class CategoryService {
    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/categories";
    private CategoryApi categoryApi;

    public CategoryService() {
        categoryApi = ServiceFactory.createRetrofitDebugService(CategoryApi.class, SERVICE_ENDPOINT);
    }

    public CategoryApi getCategoryApi() {
        return categoryApi;
    }
}
