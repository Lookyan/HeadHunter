package com.example.alex.headhunter.utils;

import android.app.Application;

import com.example.alex.headhunter.utils.ServiceHelper;

public class HHApplication extends Application {

    private ServiceHelper serviceHelper;

    public void initServiceHelper() {
        serviceHelper = new ServiceHelper(this);
    }

    public ServiceHelper getServiceHelper() {
        return serviceHelper;
    }
}