package com.aleksodem.tasklightitdemchenko.presenter.listener;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;
import com.aleksodem.tasklightitdemchenko.model.pojo.Product;

import java.util.List;

public interface IAuthorizationListener {

    void onSuccess(AuthorizationResponse response);

    void onError(String textError);

}
