package com.example.applicationtest.api;


import com.example.applicationtest.models.Verb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("verbs")
    Call<List<Verb>> getVerbs();
}