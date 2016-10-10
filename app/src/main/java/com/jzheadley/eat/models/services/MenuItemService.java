package com.jzheadley.eat.models.services;

import com.jzheadley.eat.models.services.api.MenuItemApi;

public class MenuItemService {
    //    private static final String SERVICE_ENDPOINT = "http://192.241.167.209:9000/menuItems";
    private static final String SERVICE_ENDPOINT = "http://192.99.0.20:9000/";

    private MenuItemApi menuItemApi;

    public MenuItemService() {
        menuItemApi = ServiceFactory.createRetrofitDebugService(MenuItemApi.class, SERVICE_ENDPOINT);
    }

    public MenuItemApi getMenuItemApi() {
        return menuItemApi;
    }
}
