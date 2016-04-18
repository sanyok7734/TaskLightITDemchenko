package com.aleksodem.tasklightitdemchenko.view.interfaces;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;
import com.aleksodem.tasklightitdemchenko.model.pojo.Product;

import java.util.List;

public interface ILoginView {

    void getResponseAuthorization(AuthorizationResponse response);

    void showError(String textError);
}
