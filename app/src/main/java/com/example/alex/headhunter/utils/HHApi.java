package com.example.alex.headhunter.utils;

import com.example.alex.headhunter.models.Employer;
import com.example.alex.headhunter.models.SearchResults;
import com.example.alex.headhunter.models.Vacancy;

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

    @GET("/vacancies/{id}")
    Call<Vacancy> getVacancy(@Path("id") int id);
}
