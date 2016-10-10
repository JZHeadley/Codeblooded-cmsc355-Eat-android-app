package com.jzheadley.eat.models.services;


import com.jzheadley.eat.models.services.api.UserApi;

public class UserService {
    //    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/users";
    private static final String SERVICE_ENDPOINT = "http://192.99.0.20:9000/";
    private UserApi userApi;

    public UserService() {
        userApi = ServiceFactory.createRetrofitDebugService(UserApi.class, SERVICE_ENDPOINT);
    }

    public UserApi getUserApi() {
        return userApi;
    }
}
