package com.jzheadley.eat.services.api;

import com.jzheadley.eat.models.Category;
import com.jzheadley.eat.models.ResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface CategoryApi {

    @GET
    Observable<ResponseEntity> getCategories();

    @GET("{id}")
    Observable<Category> getCategoryById(@Path("id") int categoryId);

    @POST
    Observable<Void> createCategory(@Body Category category);

    @GET
    Observable<ResponseEntity> getCategories(@Query("size") int numberOfItems);
}
