package com.aleksodem.tasklightitdemchenko.presenter.interfaces;

import com.aleksodem.tasklightitdemchenko.model.pojo.PostReview;

public interface IReviewPresenter {

    void loadReviews(int id);

    void sendReview(PostReview postReview, String token, int id);

}
