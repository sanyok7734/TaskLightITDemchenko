package com.aleksodem.tasklightitdemchenko.network;

import com.aleksodem.tasklightitdemchenko.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateService {

    private static Retrofit.Builder builder1 = new Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static CreateService ourInstance = new CreateService();

    public static CreateService getInstance() {
        return ourInstance;
    }

    private CreateService() {
    }

    public  <T> T createService(Class<T> tClass, final String token) {
        if (token != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    Request.Builder builder = request.newBuilder()
                            .header("Authorization", "Token " + token)
                            .method(request.method(), request.body());

                    Request requestFinal = builder.build();
                    return chain.proceed(requestFinal);
                }
            });
        }

        OkHttpClient okHttpClient = httpClient.build();
        Retrofit retrofit = builder1.client(okHttpClient).build();
        return retrofit.create(tClass);
    }

}
