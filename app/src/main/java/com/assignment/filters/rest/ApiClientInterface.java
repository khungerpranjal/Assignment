package com.assignment.filters.rest;

import com.assignment.filters.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 11-06-2019.
 */


/*
In API Client Interface, we define tpe of method used for the rest API
Here GET is used
 */
public interface ApiClientInterface {

    @GET("bins/zfsvs")
    Call<BaseResponse> getData();

}
