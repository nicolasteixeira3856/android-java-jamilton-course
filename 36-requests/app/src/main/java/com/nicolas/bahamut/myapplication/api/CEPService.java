package com.nicolas.bahamut.myapplication.api;

import com.nicolas.bahamut.myapplication.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CEPService {
    @GET("01001000/json/")
    Call<CEP> recuperarCEP();
}
