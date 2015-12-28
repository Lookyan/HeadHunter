package com.example.alex.headhunter;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import com.example.alex.headhunter.models.SearchResults;
import com.example.alex.headhunter.models.Vacancy;
import com.example.alex.headhunter.utils.HHApplication;
import com.example.alex.headhunter.utils.Processor;
import com.example.alex.headhunter.models.Employer;

import java.util.ArrayList;

public class NetService extends IntentService {

    public static final String EXTRA_RECEIVER = "com.example.alex.headhunter.extra.RECEIVER";

    public static final int CODE_OK = 200;
    public static final int CODE_FAILED = 500;

    public static final String RETURN_DATA_SEARCH_RESULTS = "com.example.alex.headhunter.return.SEARCH_RESULTS";

    public static final String ACTION_GET_EMPLOYER = "com.example.alex.headhunter.action.GET_EMPLOYER";
    public static final String EXTRA_EMPLOYER_ID = "com.example.alex.headhunter.extra.EMPLOYER_ID";

    public static final String ACTION_MAKE_SEARCH = "com.example.alex.headhunter.extra.MAKE_SEARCH";
    public static final String EXTRA_SEARCH_TEXT = "com.example.alex.headhunter.extra.SEARCH_TEXT";
    public static final String EXTRA_SEARCH_AREA = "com.example.alex.headhunter.extra.SEARCH_AREA";
    public static final String EXTRA_SEARCH_EXP = "com.example.alex.headhunter.extra.SEARCH_EXP";
    public static final String EXTRA_SEARCH_EMPL = "com.example.alex.headhunter.extra.SEARCH_EMPL";
    public static final String EXTRA_SEARCH_SCHED = "com.example.alex.headhunter.extra.SEARCH_SCHED";
    public static final String EXTRA_SEARCH_PAGE = "com.example.alex.headhunter.extra.SEARCH_PAGE";

    public static final String ACTION_GET_VACANCY = "com.example.alex.headhunter.action.ACTION_GET_VACANCY";
    public static final String EXTRA_VACANCY_ID = "com.example.alex.headhunter.extra.VACANCY_ID";

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
            if (ACTION_GET_EMPLOYER.equals(action)) {

                final long id = intent.getLongExtra(EXTRA_EMPLOYER_ID, -1);
                handleGetEmployer(receiver, id);

            } else if (ACTION_MAKE_SEARCH.equals(action)) {

                String text = intent.getStringExtra(EXTRA_SEARCH_TEXT);
                int areaId = intent.getIntExtra(EXTRA_SEARCH_AREA, 1);
                String experienceApiId = intent.getStringExtra(NetService.EXTRA_SEARCH_EXP);
                ArrayList<String> employmentApiIds = (ArrayList<String>) intent.getSerializableExtra(NetService.EXTRA_SEARCH_EMPL);
                ArrayList<String> scheduleApiIds = (ArrayList<String>) intent.getSerializableExtra(NetService.EXTRA_SEARCH_SCHED);
                int page = intent.getIntExtra(EXTRA_SEARCH_PAGE, 0);

                SearchResults results = processor.makeSearch(text, areaId, experienceApiId, employmentApiIds, scheduleApiIds, page);
                handleSearch(receiver, results);

            } else if (ACTION_GET_VACANCY.equals(action)) {

                int vacancy_id = intent.getIntExtra(EXTRA_VACANCY_ID, -1);
                if (vacancy_id != -1) {
                    handleGetVacancy(receiver, vacancy_id);
                }

            }
        }
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

    private void handleGetVacancy(ResultReceiver receiver, int id) {
        Vacancy vacancy = processor.getVacancy(id);
        if (vacancy != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("vacancy", vacancy);
            receiver.send(CODE_OK, bundle);
        } else {
            receiver.send(CODE_FAILED, null);
        }
    }

    private void handleSearch(ResultReceiver receiver, SearchResults results) {
        if (results != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(RETURN_DATA_SEARCH_RESULTS, results);
            receiver.send(CODE_OK, bundle);
        } else {
            receiver.send(CODE_FAILED, null);
        }
    }
}
