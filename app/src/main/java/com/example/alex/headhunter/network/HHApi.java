package com.example.alex.headhunter.network;

import com.example.alex.headhunter.models.Employer;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface HHApi {

    @GET("/employers/{id}")
    Call<Employer> getEmployer(@Path("id") long id);
}
