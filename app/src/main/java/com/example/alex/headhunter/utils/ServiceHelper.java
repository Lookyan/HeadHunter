package com.example.alex.headhunter.utils;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

import com.example.alex.headhunter.NetService;
import com.example.alex.headhunter.models.SearchResults;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceHelper implements ServiceCallbackListener {

    private ArrayList<ServiceCallbackListener> currentListeners = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private Application application;

//    caching
    private int searchRequestId;
    private SearchResults searchResults;
    private Intent searchIntent;

    public ServiceHelper(Application app) {
        this.application = app;
        currentListeners.add(this);
    }

    public void addListener(ServiceCallbackListener currentListener) {
        currentListeners.add(currentListener);
    }

    public void removeListener(ServiceCallbackListener currentListener) {
        currentListeners.remove(currentListener);
    }

    private Intent createIntent(String action, final int requestId) {
        Intent i = new Intent(application, NetService.class);
        i.setAction(action);

        i.putExtra(NetService.EXTRA_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {

                for (ServiceCallbackListener currentListener : currentListeners) {
                    if (currentListener != null) {
                        currentListener.onServiceCallback(requestId, resultCode, resultData);
                    }
                }
            }
        });

        return i;
    }

    private int createId() {
        return idCounter.getAndIncrement();
    }

    public int getEmployerInfo(long employerId) {
        final int requestId = createId();
        Intent i = createIntent(NetService.ACTION_GET_EMPLOYER, requestId);

        i.putExtra(NetService.EXTRA_EMPLOYER_ID, employerId);

        application.startService(i);
        return requestId;
    }

    public int makeSearch(String text, int areaId, String experienceApiId, ArrayList<String> employmentApiIds, ArrayList<String> scheduleApiIds) {
        final int requestId = createId();
        searchRequestId = requestId;
        Intent i = createIntent(NetService.ACTION_MAKE_SEARCH, requestId);

        i.putExtra(NetService.EXTRA_SEARCH_TEXT, text);
        i.putExtra(NetService.EXTRA_SEARCH_AREA, areaId);
        i.putExtra(NetService.EXTRA_SEARCH_EXP, experienceApiId);
        i.putExtra(NetService.EXTRA_SEARCH_EMPL, employmentApiIds);
        i.putExtra(NetService.EXTRA_SEARCH_SCHED, scheduleApiIds);

        searchIntent = new Intent(i);

        application.startService(i);
        return requestId;
    }

    public int getVacancy(int vacancyId) {
        final int requestId = createId();
        Intent i = createIntent(NetService.ACTION_GET_VACANCY, requestId);

        i.putExtra(NetService.EXTRA_VACANCY_ID, vacancyId);

        application.startService(i);
        return requestId;
    }

    public int addPageToResults() {
        if (searchResults.getPage() < searchResults.getPages()) {
            final int requestId = createId();
            Intent i = searchIntent;

            i.putExtra(NetService.EXTRA_SEARCH_PAGE, searchResults.getPage() + 1);

            application.startService(i);
            return requestId;
        }
        return -1;
    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {
        if (requestId == searchRequestId) {
            if (resultCode == NetService.CODE_OK) {
                searchResults = (SearchResults) data.getSerializable(NetService.RETURN_DATA_SEARCH_RESULTS);
            }
        }
    }
}
