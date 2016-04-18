package com.aleksodem.tasklightitdemchenko.view.interfaces;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;

import java.util.List;

public interface IListProduct {

    void showProducts(List<Product> productList);

    void showError(String textError);

}
