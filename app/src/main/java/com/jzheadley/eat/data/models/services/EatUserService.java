package com.jzheadley.eat.data.models.services;


import com.jzheadley.eat.data.models.services.api.UserApi;

public class EatUserService {
    //    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/users";
    private static final String SERVICE_ENDPOINT = "http://192.99.0.20:9000/";
    private UserApi userApi;

    public EatUserService() {
        userApi = ServiceFactory.createRetrofitDebugService(UserApi.class, SERVICE_ENDPOINT);
    }

    public UserApi getUserApi() {
        return userApi;
    }
}
