package com.example.alex.headhunter;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import com.example.alex.headhunter.utils.HHApplication;
import com.example.alex.headhunter.utils.Processor;
import com.example.alex.headhunter.models.Employer;

import java.util.ArrayList;

public class NetService extends IntentService {

    public static final String EXTRA_RECEIVER = "com.example.alex.headhunter.extra.RECEIVER";

    public static final int CODE_OK = 200;
    public static final int CODE_FAILED = 500;

    public static final String ACTION_FOO = "com.example.alex.headhunter.action.FOO";
    public static final String EXTRA_PARAM1 = "com.example.alex.headhunter.extra.PARAM1";

    public static final String ACTION_GET_EMPLOYER = "com.example.alex.headhunter.action.GET_EMPLOYER";
    public static final String EXTRA_EMPLOYER_ID = "com.example.alex.headhunter.extra.EMPLOYER_ID";

    public static final String ACTION_MAKE_SEARCH = "com.example.alex.headhunter.action.MAKE_SEARCH";
    public static final String EXTRA_SEARCH_TEXT = "com.example.alex.headhunter.action.SEARCH_TEXT";
    public static final String EXTRA_SEARCH_AREA = "com.example.alex.headhunter.action.SEARCH_AREA";
    public static final String EXTRA_SEARCH_EXP = "com.example.alex.headhunter.action.SEARCH_EXP";
    public static final String EXTRA_SEARCH_EMPL = "com.example.alex.headhunter.action.SEARCH_EMPL";
    public static final String EXTRA_SEARCH_SCHED = "com.example.alex.headhunter.action.SEARCH_SCHED";

    private Processor processor;

    public NetService() {
        super("NetService");
//        processor = new Processor((HHApplication) getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        processor = new Processor((HHApplication) getApplicationContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        processor = new Processor((HHApplication) getApplicationContext());
        if (intent != null) {
            final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {

                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionFoo(receiver, param1);

            } else if (ACTION_GET_EMPLOYER.equals(action)) {

                final long id = intent.getLongExtra(EXTRA_EMPLOYER_ID, -1);
                handleGetEmployer(receiver, id);

            } else if (ACTION_MAKE_SEARCH.equals(action)) {

                String text = intent.getStringExtra(EXTRA_SEARCH_TEXT);
                int areaId = intent.getIntExtra(EXTRA_SEARCH_AREA, 1);
                String experienceApiId = intent.getStringExtra(NetService.EXTRA_SEARCH_EXP);
                ArrayList<String> employmentApiIds = (ArrayList<String>) intent.getSerializableExtra(NetService.EXTRA_SEARCH_EMPL);
                ArrayList<String> scheduleApiIds = (ArrayList<String>) intent.getSerializableExtra(NetService.EXTRA_SEARCH_SCHED);

                processor.makeSearch(text, areaId, experienceApiId, employmentApiIds, scheduleApiIds);

            }
        }
    }

    private void handleActionFoo(ResultReceiver receiver, String param1) {
        try {
            Thread.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("haha", param1);
        receiver.send(0, bundle);
    }

    private void handleGetEmployer(ResultReceiver receiver, long id) {
        Employer employer = processor.getEmployer(id);
        if (employer != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("employer", employer);
            receiver.send(CODE_OK, bundle);
        } else {
            receiver.send(CODE_FAILED, null);
        }
    }
}
