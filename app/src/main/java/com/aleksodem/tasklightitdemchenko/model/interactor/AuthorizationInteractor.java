package com.aleksodem.tasklightitdemchenko.model.interactor;

import android.util.Log;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;
import com.aleksodem.tasklightitdemchenko.network.CreateService;
import com.aleksodem.tasklightitdemchenko.network.service.AuthorizationService;
import com.aleksodem.tasklightitdemchenko.network.service.LoginService;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IAuthorizationListener;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class AuthorizationInteractor {

    public void sendRequestAuthorization(IAuthorizationListener listener, AuthorizationRequest request) {
        AuthorizationService authorization = CreateService.getInstance().createService(AuthorizationService.class, null);
        sendRequest(authorization.request(request), listener);
    }

    public void sendRequestLogin(IAuthorizationListener listener, AuthorizationRequest request) {
        LoginService login = CreateService.getInstance().createService(LoginService.class, null);
        sendRequest(login.request(request), listener);
    }

    private void sendRequest(Observable<AuthorizationResponse> observable, final IAuthorizationListener listener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .timeout(15, TimeUnit.SECONDS)
                .subscribe(new Action1<AuthorizationResponse>() {
                    @Override
                    public void call(AuthorizationResponse response) {
                        listener.onSuccess(response);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError("Check the connection");
                    }
                });

    }

}
