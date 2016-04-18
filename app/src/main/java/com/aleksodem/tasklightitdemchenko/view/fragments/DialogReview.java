package com.aleksodem.tasklightitdemchenko.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aleksodem.tasklightitdemchenko.R;
import com.aleksodem.tasklightitdemchenko.model.pojo.PostReview;
import com.aleksodem.tasklightitdemchenko.presenter.ReviewPresenter;
import com.aleksodem.tasklightitdemchenko.utils.Constants;

public class DialogReview extends AppCompatDialogFragment implements View.OnClickListener {

    private LinearLayout linerRate;
    private TextView cancel;
    private TextView okButton;
    private EditText text;

    private int rate = 0;

    private ReviewPresenter presenter;

    private SharedPreferences sharedPreferences;
    private String token;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_review, container, false);
        getDialog().setTitle(R.string.your_review);

        Bundle bundle = getArguments();
        final int id = bundle.getInt("id");

        sharedPreferences = getActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);

        okButton = (TextView) view.findViewById(R.id.ok);
        cancel = (TextView) view.findViewById(R.id.cancelButton);
        linerRate = (LinearLayout) view.findViewById(R.id.linerRate);

        text = (EditText) view.findViewById(R.id.textReview);

        for (int i = 1; i < 6; i++) {
            addRateListener(linerRate, "rate_" + i, getActivity());
        }


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().hide();
                dismiss();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.contains(Constants.TOKEN)) {
                    token = sharedPreferences.getString(Constants.TOKEN, "");
                }
                presenter.sendReview(new PostReview(rate, text.getText().toString()), token, id);
                getDialog().hide();
            }
        });

        return view;
    }

    private void setRate(View view, String viewId, Context context) {
        int identifier = context.getResources().getIdentifier(viewId, "id", context.getPackageName());
        ImageView imageView = (ImageView) view.findViewById(identifier);
        imageView.setImageResource(R.drawable.star_yellow);
    }

    private void removeRate(View view, String viewId, Context context) {
        int identifier = context.getResources().getIdentifier(viewId, "id", context.getPackageName());
        ImageView imageView = (ImageView) view.findViewById(identifier);
        imageView.setImageResource(R.drawable.star);
    }

    private void addRateListener(View view, String viewId, Context context) {
        int identifier = context.getResources().getIdentifier(viewId, "id", context.getPackageName());
        ImageView imageView = (ImageView) view.findViewById(identifier);
        imageView.setOnClickListener(this);
    }

    public void setPresenter(ReviewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        int point = Integer.parseInt(v.getTag().toString());
        rate = point;
        for (int i = 1; i< point+1; i++) {
            setRate(linerRate, "rate_" + i, getActivity());
        }
        for (int i = point + 1; i< 6; i++) {
            removeRate(linerRate, "rate_" + i, getActivity());
        }
    }
}
