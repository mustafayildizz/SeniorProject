package com.example.seniorproject.Retrofit.Rest;

import com.example.seniorproject.Retrofit.Models.DarkSky.DarkSky;
import com.example.seniorproject.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mk on 07.01.2018.
 */

public class RestApiClient {

    Singleton singleton;
    private RestApi mRestApi;
    private RestApi getmRestApi;

    public RestApiClient(String restApiServiceUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(restApiServiceUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        singleton = Singleton.getInstance();
        getmRestApi = retrofit.create(RestApi.class);

        mRestApi = retrofit.create(RestApi.class);
    }

    public RestApi getRestApi() {
        return mRestApi;
    }
}