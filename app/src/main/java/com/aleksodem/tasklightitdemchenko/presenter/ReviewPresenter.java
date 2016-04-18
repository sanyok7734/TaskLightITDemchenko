package com.aleksodem.tasklightitdemchenko.presenter;

import com.aleksodem.tasklightitdemchenko.model.interactor.ReviewInteractor;
import com.aleksodem.tasklightitdemchenko.model.pojo.PostReview;
import com.aleksodem.tasklightitdemchenko.model.pojo.Review;
import com.aleksodem.tasklightitdemchenko.presenter.interfaces.IReviewPresenter;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IListLoadListener;
import com.aleksodem.tasklightitdemchenko.presenter.listener.ISendReviewListener;
import com.aleksodem.tasklightitdemchenko.view.interfaces.IReviewView;

import java.util.List;

public class ReviewPresenter implements IReviewPresenter, IListLoadListener<Review>, ISendReviewListener {

    private IReviewView view;
    private ReviewInteractor interactor;

    public ReviewPresenter(IReviewView view) {
        this.view = view;
        this.interactor = new ReviewInteractor();
    }

    @Override
    public void onSuccess(List<Review> list) {
        view.getReview(list);
    }

    @Override
    public void onSuccess() {
        view.updateReview();
    }

    @Override
    public void onError(String textError) {
        view.showError(textError);
    }

    @Override
    public void loadReviews(int id) {
        interactor.loadListReview(id, this);
    }

    @Override
    public void sendReview(PostReview postReview, String token, int id) {
        interactor.sendReview(id, postReview, token, this);
    }

}
