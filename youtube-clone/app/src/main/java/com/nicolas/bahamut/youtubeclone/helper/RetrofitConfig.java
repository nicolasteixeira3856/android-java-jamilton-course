package com.nicolas.bahamut.youtubeclone.helper;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(YoutubeConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
