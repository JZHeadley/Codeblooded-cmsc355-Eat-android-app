package com.jzheadley.eat.data.services;

import com.jzheadley.eat.data.services.api.MenuApi;

public class MenuService {
    //    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/menus";
    private static final String SERVICE_ENDPOINT = "http://192.99.0.20:9000/";

    private MenuApi menuApi;

    public MenuService() {
        menuApi = ServiceFactory.createRetrofitDebugService(MenuApi.class, SERVICE_ENDPOINT);
    }

    public MenuApi getMenuApi() {
        return menuApi;
    }
}
