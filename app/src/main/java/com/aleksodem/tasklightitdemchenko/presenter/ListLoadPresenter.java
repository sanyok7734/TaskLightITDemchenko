package com.aleksodem.tasklightitdemchenko.presenter;

import android.content.Context;

import com.aleksodem.tasklightitdemchenko.model.interactor.ListLoadInteractor;
import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.presenter.interfaces.IListLoadPresenter;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IListLoadListener;
import com.aleksodem.tasklightitdemchenko.view.interfaces.IListProduct;

import java.util.List;

public class ListLoadPresenter implements IListLoadPresenter, IListLoadListener<Product> {

    private IListProduct view;
    private ListLoadInteractor interactor;

    public ListLoadPresenter(IListProduct view) {
        this.interactor = new ListLoadInteractor();
        this.view = view;
    }

    @Override
    public void loadListProduct() {
        interactor.loadListProduct(this);
    }

    @Override
    public void onSuccess(List<Product> products) {
        view.showProducts(products);
    }

    @Override
    public void onError(String textError) {
        view.showError(textError);
    }
}
