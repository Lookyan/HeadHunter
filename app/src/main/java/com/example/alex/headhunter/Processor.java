package com.example.alex.headhunter;

import com.example.alex.headhunter.NetworkApi.HHApi;
import com.example.alex.headhunter.models.Employer;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Processor {

    HHApi hhApi;

    public Processor() {
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
}
