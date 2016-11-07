package com.jzheadley.eat.data.services;

import com.jzheadley.eat.data.services.api.RestaurantApi;
import com.jzheadley.eat.utils.Constants;


public class RestaurantService {

    private RestaurantApi restaurantApi;

    public RestaurantService() {
        restaurantApi = ServiceFactory
            .createRetrofitDebugService(RestaurantApi.class, Constants.SERVICE_ENDPOINT);
    }

    public RestaurantApi getRestaurantApi() {
        return restaurantApi;
    }
}
