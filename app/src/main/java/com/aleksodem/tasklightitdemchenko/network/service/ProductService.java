package com.aleksodem.tasklightitdemchenko.network.service;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ProductService {

    @GET("/api/products/")
    Observable<List<Product>> getProduct();

}
