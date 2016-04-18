package com.aleksodem.tasklightitdemchenko.presenter.interfaces;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;

public interface IAuthorizationPresenter {

    void authorizationRequest(AuthorizationRequest authorizationRequest);
    void loginRequest(AuthorizationRequest authorizationRequest);

}
