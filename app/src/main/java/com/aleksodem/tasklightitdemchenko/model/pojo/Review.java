package com.aleksodem.tasklightitdemchenko.model.pojo;

public class Review {

    private int rate;
    private String text;
    private User created_by;
    private String created_at;

    public Review(int rate, String text, User created_by, String created_at) {
        this.rate = rate;
        this.text = text;
        this.created_by = created_by;
        this.created_at = created_at;
    }

    public int getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public User getCreated_by() {
        return created_by;
    }

    public String getUser() {
        return created_by.getUsername();
    }

    @Override
    public String toString() {
        return "Review{" +
                "rate=" + rate +
                ", text='" + text + '\'' +
                ", created_by=" + created_by.getUsername() +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    class User {
        private String username;

        public User(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
