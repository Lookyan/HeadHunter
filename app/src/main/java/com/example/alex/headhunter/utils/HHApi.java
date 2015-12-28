package com.example.alex.headhunter.utils;

import com.example.alex.headhunter.models.Employer;
import com.example.alex.headhunter.models.SearchResults;
import com.example.alex.headhunter.models.Vacancy;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface HHApi {

    @GET("/employers/{id}")
    Call<Employer> getEmployer(@Path("id") long id);

    @GET("/vacancies")
    Call<SearchResults> makeSearch(@Query("text") String text, @Query("area") int areaId,
                                   @Query("experience") String experience,
                                   @Query("employment") List<String> employment,
                                   @Query("schedule") List<String> schedule,
                                   @Query("page") int page);

    @GET("/vacancies/{id}")
    Call<Vacancy> getVacancy(@Path("id") int id);
}
