package com.aleksodem.tasklightitdemchenko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationRequest;
import com.aleksodem.tasklightitdemchenko.model.pojo.AuthorizationResponse;
import com.aleksodem.tasklightitdemchenko.presenter.AuthorizationPresenter;
import com.aleksodem.tasklightitdemchenko.presenter.interfaces.IAuthorizationPresenter;
import com.aleksodem.tasklightitdemchenko.utils.Constants;
import com.aleksodem.tasklightitdemchenko.view.interfaces.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.cardRegistration)
    FrameLayout cardRegistration;

    @Bind(R.id.cardLogin)
    FrameLayout cardLogin;

    @Bind(R.id.login)
    EditText login;
    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.loginRegister)
    EditText loginRegister;
    @Bind(R.id.passwordRegister)
    EditText passwordRegister;
    @Bind(R.id.repeatPasswordRegister)
    EditText repeatPasswordRegister;

    private IAuthorizationPresenter presenter;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new AuthorizationPresenter(this);

        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);
        if (sharedPreferences.contains(Constants.AUTHORIZATION)) {
            boolean isAuthorization = sharedPreferences.getBoolean(Constants.AUTHORIZATION, false);
            if (isAuthorization) {
                Intent intentMainActivity = new Intent(this, ListProductActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        }
    }


    public void signUpButtonClick(View view) {
        int cx = (cardLogin.getLeft() + cardLogin.getRight()) / 2;
        int cy = (cardLogin.getTop() + cardLogin.getBottom()) / 2;
        int dx = Math.max(cx, cardLogin.getWidth() - cx);
        int dy = Math.max(cy, cardLogin.getHeight() - cy);

        float finalRadius = (float) Math.hypot(dx, dy) + 100;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(cardRegistration, dx, dy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                cardRegistration.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd() {
                cardLogin.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });

        animator.start();
    }

    public void closeRegisterCardClick(View view) {
        int cx = (cardRegistration.getLeft() + cardRegistration.getRight()) / 2;
        int cy = (cardRegistration.getTop() + cardRegistration.getBottom()) / 2;
        int dx = Math.max(cx, cardRegistration.getWidth() - cx);
        int dy = Math.max(cy, cardRegistration.getHeight() - cy);

        float finalRadius = (float) Math.hypot(dx, dy) + 100;

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(cardRegistration, dx, dy, finalRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(400);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                cardLogin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd() {
                cardRegistration.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });

        animator.start();
    }

    public void skipSignIn(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.AUTHORIZATION, false);
        editor.apply();

        Intent intentMainActivity = new Intent(this, ListProductActivity.class);
        startActivity(intentMainActivity);
    }

    public void signIn(View view) {
        if (login.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Check the data", Toast.LENGTH_SHORT).show();
        } else {
            presenter.loginRequest(new AuthorizationRequest(login.getText().toString(), password.getText().toString()));
        }
    }

    public void signUp(View view) {
        if (loginRegister.getText().toString().equals("") || passwordRegister.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Check the data", Toast.LENGTH_SHORT).show();
        } else if (!passwordRegister.getText().toString().equals(repeatPasswordRegister.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Check your password", Toast.LENGTH_SHORT).show();
        } else {
            presenter.authorizationRequest(new AuthorizationRequest(loginRegister.getText().toString(), passwordRegister.getText().toString()));
        }
    }

    @Override
    public void getResponseAuthorization(AuthorizationResponse response) {
        if (response.isSuccess()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.AUTHORIZATION, response.isSuccess());
            editor.putString(Constants.TOKEN, response.getToken());
            editor.apply();

            Intent intentMainActivity = new Intent(this, ListProductActivity.class);
            startActivity(intentMainActivity);
            finish();
        } else {
            Toast.makeText(this, "Authorisation Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String textError) {
        Toast.makeText(this, textError, Toast.LENGTH_SHORT).show();
    }
}

