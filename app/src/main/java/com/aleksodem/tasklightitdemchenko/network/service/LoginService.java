package com.aleksodem.tasklightitdemchenko.network.service;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService {

    @POST("/api/login/")
    Observable<AuthorizationResponse> request(@Body AuthorizationRequest authorizationRequest);
}
