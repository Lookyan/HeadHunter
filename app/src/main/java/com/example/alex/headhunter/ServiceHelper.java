package com.example.alex.headhunter;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

import com.example.alex.headhunter.models.Employer;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceHelper {

    private ArrayList<ServiceCallbackListener> currentListeners = new ArrayList<ServiceCallbackListener>();

    private AtomicInteger idCounter = new AtomicInteger();

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

    private Intent createIntent(String action, final int requestId) {
        Intent i = new Intent(application, NetService.class);
        i.setAction(action);

        i.putExtra(NetService.EXTRA_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
//                Intent originalIntent = pendingActivities.get(requestId);
//                if (isPending(requestId)) {
//                    pendingActivities.remove(requestId);

                for (ServiceCallbackListener currentListener : currentListeners) {
                    if (currentListener != null) {
                        currentListener.onServiceCallback(requestId, resultCode, resultData);
                    }
                }
//                }
            }
        });

        return i;
    }

    private int createId() {
        return idCounter.getAndIncrement();
    }

//    private int runRequest(final int requestId, Intent i) {
////        pendingActivities.append(requestId, i);
//        application.startService(i);
//        return requestId;
//    }

    public int doAwesomeAction(String text) {
        final int requestId = createId();
        Intent i = createIntent(NetService.ACTION_FOO, requestId);

        i.putExtra(NetService.EXTRA_PARAM1, text);

        application.startService(i);
        return requestId;
    }

    public int getEmployerInfo(long employerId) {
        final int requestId = createId();
        Intent i = createIntent(NetService.ACTION_GET_EMPLOYER, requestId);

        i.putExtra(NetService.EXTRA_EMPLOYER_ID, employerId);

        application.startService(i);
        return requestId;
    }
}
