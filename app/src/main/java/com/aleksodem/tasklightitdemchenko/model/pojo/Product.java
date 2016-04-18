package com.aleksodem.tasklightitdemchenko.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private int id;
    private String img;
    private String text;
    private String title;

    public Product(int id, String img, String text, String title) {
        this.id = id;
        this.img = img;
        this.text = text;
        this.title = title;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        img = in.readString();
        text = in.readString();
        title = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(img);
        dest.writeString(text);
        dest.writeString(title);
    }
}
