package com.assignment.filters.rest;

import com.assignment.filters.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11-06-2019.
 */

public class ApiClient {
    static Retrofit retrofit;
    private static final String API_BASE_URL = "https://api.myjson.com/";

    private ApiClient() {

    }

/*
build retrofit object by calling createInstance() function
 */
    public static Retrofit getApiClient() {
        if (retrofit == null) {
            createInstance();
        } else {
            return retrofit;
        }
        return retrofit;
    }

    /*
    Retrofit is build to call the API url with the help of HTTPClient
     */

    private static void createInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
