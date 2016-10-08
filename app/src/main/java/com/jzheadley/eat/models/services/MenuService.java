package com.jzheadley.eat.models.services;

import com.jzheadley.eat.models.services.api.MenuApi;

public class MenuService {
    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/menus";
    private MenuApi menuApi;

    public MenuService() {
        menuApi = ServiceFactory.createRetrofitDebugService(MenuApi.class, SERVICE_ENDPOINT);
    }

    public MenuApi getMenuApi() {
        return menuApi;
    }
}
