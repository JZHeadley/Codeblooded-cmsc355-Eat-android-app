package com.jzheadley.eat.services;

import retrofit2.Retrofit;

/**
 * Created by zephy on 10/5/2016.
 */

public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .build();
        T service = retrofit.create(clazz);

        return service;
    }
}
