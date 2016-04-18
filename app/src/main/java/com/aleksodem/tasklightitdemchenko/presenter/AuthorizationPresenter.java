package com.aleksodem.tasklightitdemchenko.presenter;

import com.aleksodem.tasklightitdemchenko.model.interactor.AuthorizationInteractor;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;
import com.aleksodem.tasklightitdemchenko.presenter.interfaces.IAuthorizationPresenter;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IAuthorizationListener;
import com.aleksodem.tasklightitdemchenko.view.interfaces.ILoginView;

public class AuthorizationPresenter implements IAuthorizationListener, IAuthorizationPresenter {

    private ILoginView view;
    private AuthorizationInteractor interactor;

    public AuthorizationPresenter(ILoginView view) {
        this.view = view;
        this.interactor = new AuthorizationInteractor();
    }

    @Override
    public void authorizationRequest(AuthorizationRequest authorizationRequest) {
        interactor.sendRequestAuthorization(this, authorizationRequest);
    }

    @Override
    public void loginRequest(AuthorizationRequest authorizationRequest) {
        interactor.sendRequestLogin(this, authorizationRequest);
    }

    @Override
    public void onSuccess(AuthorizationResponse response) {
        view.getResponseAuthorization(response);
    }

    @Override
    public void onError(String textError) {
        view.showError(textError);
    }
}
