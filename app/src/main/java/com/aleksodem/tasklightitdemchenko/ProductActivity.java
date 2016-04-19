package com.aleksodem.tasklightitdemchenko;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.model.pojo.Review;
import com.aleksodem.tasklightitdemchenko.presenter.ReviewPresenter;
import com.aleksodem.tasklightitdemchenko.utils.Constants;
import com.aleksodem.tasklightitdemchenko.view.fragments.DialogReview;
import com.aleksodem.tasklightitdemchenko.view.interfaces.IReviewView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity implements IReviewView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.listReview)
    LinearLayout listReview;

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.reviewButton)
    FloatingActionButton reviewButton;

    private String token;
    private boolean isAuthorization;
    private SharedPreferences sharedPreferences;


    private ReviewPresenter presenter;
    private Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);

        isAuthorization = sharedPreferences.getBoolean(Constants.AUTHORIZATION, false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        product = intent.getParcelableExtra("product");

        Picasso.with(this)
                .load(Constants.IMG_URL + product.getImg())
                .into(image);

        title.setText(product.getTitle());
        description.setText(product.getText());

        presenter = new ReviewPresenter(this);
        presenter.loadReviews(product.getId());

        if (!isAuthorization) {
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) reviewButton.getLayoutParams();
            p.setBehavior(null);
            reviewButton.setLayoutParams(p);
            reviewButton.hide();
        } else {
            reviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogReview dialogFragment = new DialogReview();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", product.getId());
                    dialogFragment.setArguments(bundle);
                    dialogFragment.setPresenter(presenter);
                    dialogFragment.show(getSupportFragmentManager(), "reviewDialog");
                }
            });
        }
    }

    @Override
    public void getReview(List<Review> reviews) {
        Collections.reverse(reviews);
        listReview.removeAllViews();
        for (Review review : reviews) {
            LayoutInflater inflater = LayoutInflater.from(ProductActivity.this);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.item_review, null, false);

            TextView user = (TextView) linearLayout.findViewById(R.id.user);
            TextView data = (TextView) linearLayout.findViewById(R.id.data);
            TextView text = (TextView) linearLayout.findViewById(R.id.text);

            for (int i = 1; i < review.getRate() + 1; i++) {
                addRate(linearLayout, "rate" + i, this);
            }


            user.setText(review.getUser());
            data.setText(review.getCreated_at().split("T")[0]);
            text.setText(review.getText());

            listReview.addView(linearLayout);
        }
    }

    private void addRate(View view, String viewId, Context context) {
        int identifier = context.getResources().getIdentifier(viewId, "id", context.getPackageName());
        ImageView imageView = (ImageView) view.findViewById(identifier);
        imageView.setImageResource(R.drawable.star_yellow);
    }

    @Override
    public void showError(String textError) {
        Toast.makeText(this, textError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateReview() {
        Toast.makeText(this, "Review sent", Toast.LENGTH_SHORT).show();
        presenter = new ReviewPresenter(this);
        presenter.loadReviews(product.getId());
    }

}
