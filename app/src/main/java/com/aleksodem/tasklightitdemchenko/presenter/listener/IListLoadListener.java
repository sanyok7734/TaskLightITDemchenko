package com.aleksodem.tasklightitdemchenko.presenter.listener;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;

import java.util.List;

public interface IListLoadListener<T> {

    void onSuccess(List<T> list);

    void onError(String textError);

}
