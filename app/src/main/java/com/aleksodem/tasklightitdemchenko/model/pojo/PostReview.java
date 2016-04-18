package com.aleksodem.tasklightitdemchenko.model.pojo;

public class PostReview {

    private int rate;
    private String text;

    public PostReview(int rate, String text) {
        this.rate = rate;
        this.text = text;
    }

    public int getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "PostReview{" +
                "rate=" + rate +
                ", text='" + text + '\'' +
                '}';
    }
}
