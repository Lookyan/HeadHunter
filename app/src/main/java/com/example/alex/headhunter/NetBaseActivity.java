package com.example.alex.headhunter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class NetBaseActivity extends AppCompatActivity implements ServiceCallbackListener {

    private ServiceHelper serviceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceHelper = ((HHApplication) getApplication()).getServiceHelper();

        if (serviceHelper == null) {
            ((HHApplication) getApplication()).initServiceHelper();
            serviceHelper = ((HHApplication) getApplication()).getServiceHelper();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        serviceHelper.addListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        serviceHelper.removeListener(this);
    }

    public ServiceHelper getServiceHelper() {
        return serviceHelper;
    }

}
