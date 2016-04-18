package com.aleksodem.tasklightitdemchenko.network.service;

import com.aleksodem.tasklightitdemchenko.model.pojo.PostReview;
import com.aleksodem.tasklightitdemchenko.model.pojo.Review;
import com.aleksodem.tasklightitdemchenko.model.pojo.ReviewResponse;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface ReviewService {

    @GET("/api/reviews/{id}")
    Observable<List<Review>> getReviews(@Path("id") int id);

    @POST("/api/reviews/{id}")
    Observable<ReviewResponse> sendReviews(@Path("id") int id, @Body PostReview review);
}
