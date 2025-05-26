package com.example.applicationtest.api;


import com.example.applicationtest.auth.models.RegisterRequest;
import com.example.applicationtest.auth.models.RegisterResponse;
import com.example.applicationtest.models.Verb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("verbs")
    Call<List<Verb>> getVerbs();


    @POST("auth/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}