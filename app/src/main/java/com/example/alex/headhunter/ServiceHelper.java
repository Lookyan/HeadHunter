package com.example.alex.headhunter;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

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

    private Intent createIntent(final Context context, String actionLogin, final int requestId) {
        Intent i = new Intent(context, WorkerService.class);
        i.setAction(actionLogin);

        i.putExtra(WorkerService.EXTRA_STATUS_RECEIVER, new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
//                Intent originalIntent = pendingActivities.get(requestId);
//                if (isPending(requestId)) {
//                    pendingActivities.remove(requestId);

                    for (ServiceCallbackListener currentListener : currentListeners) {
                        if (currentListener != null) {
                            currentListener.onServiceCallback(requestId, originalIntent, resultCode, resultData);
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

    private int runRequest(final int requestId, Intent i) {
//        pendingActivities.append(requestId, i);
        application.startService(i);
        return requestId;
    }

    public int doAwesomeAction(long personId) {
        final int requestId = createId();
        Intent i = createIntent(application, AwesomeHandler.ACTION_AWESOME_ACTION, requestId);
        i.putExtra(AwesomeHandler.EXTRA_PERSON_ID, personId);
        return runRequest(requestId, i);
    }
}
