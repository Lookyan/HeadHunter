package com.example.alex.headhunter;


import android.app.Application;
import android.content.Intent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceHelper {

    private ArrayList<ServiceCallbackListener> currentListeners = new ArrayList<ServiceCallbackListener>();

//    private AtomicInteger idCounter = new AtomicInteger();

//    private SparseArray<Intent> pendingActivities = new SparseArray<Intent>();

    private Application application;

    ServiceHelper(Application app) {
        this.application = app;
    }

    public void addListener(ServiceCallbackListener currentListener) {
        currentListeners.add(currentListener);
    }

    public void removeListener(ServiceCallbackListener currentListener) {
        currentListeners.remove(currentListener);
    }
}
