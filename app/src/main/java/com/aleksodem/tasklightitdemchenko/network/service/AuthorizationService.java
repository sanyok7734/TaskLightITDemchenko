package com.aleksodem.tasklightitdemchenko.network.service;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthorizationService {

    @POST("/api/register/")
    Observable<AuthorizationResponse> request(@Body AuthorizationRequest authorizationRequest);

   /* @POST("/api/register/")
    Observable<AuthorizationResponse> signUp(@Body AuthorizationRequest authorizationRequest);

    @POST("/api/login/")
    Observable<AuthorizationResponse> signIn(@Body AuthorizationRequest authorizationRequest);*/

}
