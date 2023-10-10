package com.example.parcialuno.api;

import android.util.Log;
import com.example.parcialuno.dto.ProductList;
import com.example.parcialuno.dto.ProductSingle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("products")
    Call<ProductList> findAll();

    @GET("products/{id}")
    Call<ProductSingle> find(@Path("id") int id);
}
