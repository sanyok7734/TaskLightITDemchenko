package com.aleksodem.tasklightitdemchenko.model.pojo;

public class ReviewResponse {

    private int review_id;

    public ReviewResponse(int review_id) {
        this.review_id = review_id;
    }

    public int getReview_id() {
        return review_id;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "review_id=" + review_id +
                '}';
    }
}
