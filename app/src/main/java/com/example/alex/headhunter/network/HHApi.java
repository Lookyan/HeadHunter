package com.example.alex.headhunter.network;

import com.example.alex.headhunter.models.Employer;
import com.example.alex.headhunter.models.SearchResults;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface HHApi {

    @GET("/employers/{id}")
    Call<Employer> getEmployer(@Path("id") long id);

    @GET("/vacancies")
    Call<SearchResults> makeSearch(@QueryMap Map<String, String> options);
}
