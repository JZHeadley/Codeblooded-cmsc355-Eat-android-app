package com.jzheadley.eat.data.services;


import com.jzheadley.eat.data.services.api.CategoryApi;
import com.jzheadley.eat.utils.Constants;


public class CategoryService {
    private CategoryApi categoryApi;

    public CategoryService() {
        categoryApi = ServiceFactory
                .createRetrofitDebugService(CategoryApi.class, Constants.SERVICE_ENDPOINT);
    }

    public CategoryApi getCategoryApi() {
        return categoryApi;
    }
}
