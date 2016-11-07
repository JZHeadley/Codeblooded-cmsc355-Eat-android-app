package com.jzheadley.eat.data.services;

import com.jzheadley.eat.data.services.api.MenuItemApi;
import com.jzheadley.eat.utils.Constants;


public class MenuItemService {

    private MenuItemApi menuItemApi;

    public MenuItemService() {
        menuItemApi = ServiceFactory
            .createRetrofitDebugService(MenuItemApi.class, Constants.SERVICE_ENDPOINT);
    }

    public MenuItemApi getMenuItemApi() {
        return menuItemApi;
    }
}
