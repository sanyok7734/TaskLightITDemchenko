package com.aleksodem.tasklightitdemchenko.model.interactor;

import android.util.Log;

import com.aleksodem.tasklightitdemchenko.model.pojo.PostReview;
import com.aleksodem.tasklightitdemchenko.model.pojo.Review;
import com.aleksodem.tasklightitdemchenko.model.pojo.ReviewResponse;
import com.aleksodem.tasklightitdemchenko.network.CreateService;
import com.aleksodem.tasklightitdemchenko.network.service.ReviewService;
import com.aleksodem.tasklightitdemchenko.presenter.listener.IListLoadListener;
import com.aleksodem.tasklightitdemchenko.presenter.listener.ISendReviewListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ReviewInteractor {

    public void loadListReview(int id, final IListLoadListener<Review> listener) {
        ReviewService service = CreateService.getInstance().createService(ReviewService.class, null);
        service.getReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .timeout(15, TimeUnit.SECONDS)
                .subscribe(new Action1<List<Review>>() {
                    @Override
                    public void call(List<Review> reviews) {
                        for (Review review : reviews) {
                            Log.d("REVIEW", review.toString());
                        }
                        listener.onSuccess(reviews);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError("Check the connection");
                    }
                });
    }

    public void sendReview(int id, PostReview review, String token, final ISendReviewListener listener) {
        ReviewService service = CreateService.getInstance().createService(ReviewService.class, token);
        service.sendReviews(id, review)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .timeout(15, TimeUnit.SECONDS)
                .subscribe(new Action1<ReviewResponse>() {
                    @Override
                    public void call(ReviewResponse reviewResponse) {
                        listener.onSuccess();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        listener.onError("Review not submitted");
                    }
                });
    }

}
