package com.jzheadley.eat.data.services;

import com.jzheadley.eat.data.services.api.MenuApi;
import com.jzheadley.eat.utils.Constants;



public class MenuService {

    private MenuApi menuApi;

    public MenuService() {
        menuApi = ServiceFactory.createRetrofitDebugService(MenuApi.class, Constants.SERVICE_ENDPOINT);
    }

    public MenuApi getMenuApi() {
        return menuApi;
    }
}
