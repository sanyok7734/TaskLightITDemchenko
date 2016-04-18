package com.aleksodem.tasklightitdemchenko.model.interactor;

import android.content.Context;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.network.CreateService;
import com.aleksodem.tasklightitdemchenko.network.service.ProductService;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IListLoadListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ListLoadInteractor {

    public void loadListProduct(final IListLoadListener listener) {
        ProductService service = CreateService.getInstance().createService(ProductService.class, null);
        service.getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .timeout(15, TimeUnit.SECONDS)
                .subscribe(new Action1<List<Product>>() {
                    @Override
                    public void call(List<Product> products) {
                        listener.onSuccess(products);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError("Check the connection");
                    }
                });
    }

}
