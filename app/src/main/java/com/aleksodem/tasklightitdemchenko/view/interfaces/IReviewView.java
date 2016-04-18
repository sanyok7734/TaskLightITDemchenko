package com.aleksodem.tasklightitdemchenko.view.interfaces;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.model.pojo.Review;

import java.util.List;

public interface IReviewView {

    void getReview(List<Review> reviews);

    void showError(String textError);

    void updateReview();

}
