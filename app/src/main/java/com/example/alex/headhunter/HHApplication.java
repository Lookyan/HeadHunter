package com.example.alex.headhunter;

import android.app.Application;

public class HHApplication extends Application {

    private ServiceHelper serviceHelper;

    public void initServiceHelper() {
        serviceHelper = new ServiceHelper(this);
    }

    public ServiceHelper getServiceHelper() {
        return serviceHelper;
    }
}