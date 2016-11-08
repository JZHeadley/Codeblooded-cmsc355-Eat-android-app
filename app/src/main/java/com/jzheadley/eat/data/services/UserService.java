package com.jzheadley.eat.data.services;


import com.jzheadley.eat.data.services.api.UserApi;
import com.jzheadley.eat.utils.Constants;


public class UserService {
    private UserApi userApi;

    public UserService() {
        userApi = ServiceFactory.createRetrofitDebugService(UserApi.class,
                Constants.SERVICE_ENDPOINT);
    }

    public UserApi getUserApi() {
        return userApi;
    }
}
