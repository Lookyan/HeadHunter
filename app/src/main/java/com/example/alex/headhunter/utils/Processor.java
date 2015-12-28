package com.example.alex.headhunter.utils;

import android.content.ContentValues;
import android.net.Uri;

import com.example.alex.headhunter.content.contracts.SearchResultContract;
import com.example.alex.headhunter.models.SearchResults;
import com.example.alex.headhunter.models.Vacancy;
import com.example.alex.headhunter.models.VacancyShort;
import com.example.alex.headhunter.models.Employer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Processor {

    private HHApi hhApi;
    private HHApplication context;
    private final Uri CONTENT_SEARCH_RESULTS_URI = Uri.parse("content://com.example.alex.headhunter.provider/search_result");

    public Processor(HHApplication context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hh.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        hhApi = retrofit.create(HHApi.class);
    }

    public Employer getEmployer(long id) {
        Response<Employer> response;
        try {
            response = hhApi.getEmployer(id).execute();
        } catch (IOException e) {
            return null;
        }
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    public Vacancy getVacancy(int id) {
        Response<Vacancy> response;
        try {
            response = hhApi.getVacancy(id).execute();
        } catch (IOException e) {
            return null;
        }
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    public SearchResults makeSearch(String text, int areaId, String experienceApiId, ArrayList<String> employmentApiIds, ArrayList<String> scheduleApiIds, int page) {
        Response<SearchResults> response;
        try {
            response = hhApi.makeSearch(text, areaId, experienceApiId, employmentApiIds, scheduleApiIds, page).execute();
        } catch (IOException e) {
            return null;
        }
        if (response.isSuccess()) {
            SearchResults results = response.body();
//            context.getContentResolver().delete(CONTENT_SEARCH_RESULTS_URI, null, null);

            ContentValues contentValues = new ContentValues();

            for (VacancyShort vacancy : results.getItems()) {
                contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_VACANCY_ID, vacancy.getId());
                contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_NAME, vacancy.getName());
                contentValues.put(SearchResultContract.SearchResultEntry.COLUMN_NAME_EMPLOYER_NAME, vacancy.getEmployer().getName());

                context.getContentResolver().insert(CONTENT_SEARCH_RESULTS_URI, contentValues);
            }
            return results;
        }
        return null;
    }
}
