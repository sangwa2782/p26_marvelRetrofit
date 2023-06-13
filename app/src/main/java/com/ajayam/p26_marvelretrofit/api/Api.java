package com.ajayam.p26_marvelretrofit.api;

import com.ajayam.p26_marvelretrofit.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    public static String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Results>> getsuperHeroes();
}
