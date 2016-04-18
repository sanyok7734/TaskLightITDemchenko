package com.aleksodem.tasklightitdemchenko;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aleksodem.tasklightitdemchenko.model.pojo.Product;
import com.aleksodem.tasklightitdemchenko.presenter.ListLoadPresenter;
import com.aleksodem.tasklightitdemchenko.presenter.interfaces.IListLoadPresenter;
import com.aleksodem.tasklightitdemchenko.utils.Constants;
import com.aleksodem.tasklightitdemchenko.view.IListProductListener;
import com.aleksodem.tasklightitdemchenko.view.adapters.AdapterListProduct;
import com.aleksodem.tasklightitdemchenko.view.interfaces.IListProduct;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListProductActivity extends AppCompatActivity implements IListProductListener, IListProduct {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.listProduct)
    RecyclerView listProduct;

    private AdapterListProduct adapterListProduct;
    private IListLoadPresenter presenter;

    private String token;
    private boolean isAuthorization;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, MODE_PRIVATE);

        setSupportActionBar(toolbar);

        settingsList();

        if (sharedPreferences.contains(Constants.AUTHORIZATION)) {
            isAuthorization = sharedPreferences.getBoolean(Constants.AUTHORIZATION, false);
        }


        if (!isAuthorization && toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        presenter = new ListLoadPresenter(this);
        presenter.loadListProduct();
    }

    private void settingsList() {
        adapterListProduct = new AdapterListProduct(getApplicationContext(), this);

        listProduct.setLayoutManager(new LinearLayoutManager(this));
        listProduct.setAdapter(adapterListProduct);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isAuthorization)
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.log_out) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.AUTHORIZATION, false);
            editor.putString(Constants.TOKEN, null);
            editor.apply();

            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openInfoProduct(int idProduct) {
        Product product = null;
        for (Product productItem : adapterListProduct.getProducts()) {
            if (productItem.getId() == idProduct) {
                product = productItem;
            }
        }
        if (isNetworkAvailable(getApplicationContext())) {
            Intent intentProductActivity = new Intent(this, ProductActivity.class);
            intentProductActivity.putExtra("product", product);
            startActivity(intentProductActivity);
        } else {
            Toast.makeText(ListProductActivity.this, "Check the connection", Toast.LENGTH_SHORT).show();
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void showProducts(List<Product> productList) {
        adapterListProduct.addList(productList);
    }

    @Override
    public void showError(String textError) {
        Toast.makeText(ListProductActivity.this, textError, Toast.LENGTH_SHORT).show();
    }
}
