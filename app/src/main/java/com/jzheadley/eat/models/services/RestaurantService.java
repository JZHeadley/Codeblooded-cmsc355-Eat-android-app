package com.jzheadley.eat.models.services;

import com.jzheadley.eat.models.services.api.RestaurantApi;

public class RestaurantService {
    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/";
    private RestaurantApi restaurantApi;

    public RestaurantService() {
        restaurantApi = ServiceFactory.createRetrofitDebugService(RestaurantApi.class, SERVICE_ENDPOINT);
    }

    public RestaurantApi getRestaurantApi() {
        return restaurantApi;
    }
}
