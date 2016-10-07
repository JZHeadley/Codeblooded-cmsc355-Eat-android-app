package com.jzheadley.eat.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .build();
        return retrofit.create(clazz);
    }

    public static <T> T createRetrofitDebugService(final Class<T> clazz, final String endPoint) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);
        builder.build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        OkHttpClient okHttpClient = builder.build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit.create(clazz);
    }
}
