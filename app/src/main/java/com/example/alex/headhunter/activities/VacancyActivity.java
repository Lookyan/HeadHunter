package com.example.alex.headhunter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.alex.headhunter.NetBaseActivity;
import com.example.alex.headhunter.R;
import com.example.alex.headhunter.models.Vacancy;

public class VacancyActivity extends NetBaseActivity {

    public static final String EXTRA_VACANCY_ID = "com.example.alex.headhunter.extra.VACANCY_ID";

    private int vacancyId;
    private int requestId;
    private Vacancy vacancy;

    private TextView nameView;
    private TextView companyView;
    private TextView salaryView;
    private TextView areaView;
    private TextView experienceView;
    private WebView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent incoming_intent = getIntent();
        if (incoming_intent != null) {
            vacancyId = incoming_intent.getIntExtra(EXTRA_VACANCY_ID, -1);
        }
        if (vacancyId != -1) {
            requestId = getServiceHelper().getVacancy(vacancyId);
        }

        nameView = (TextView) findViewById(R.id.name);
        companyView = (TextView) findViewById(R.id.company);
        salaryView = (TextView) findViewById(R.id.salary);
        areaView = (TextView) findViewById(R.id.area);
        experienceView = (TextView) findViewById(R.id.experience);
        descriptionView = (WebView) findViewById(R.id.description);
    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {
        if (requestId == this.requestId) {
            if (resultCode == 200) {
                vacancy = (Vacancy) data.getSerializable("vacancy");
                updateVacancyInfo();
            }
        }
    }

    private void updateVacancyInfo() {
        if (vacancy != null) {
            if (vacancy.getName() != null) {
                nameView.setText(vacancy.getName());
            }
            if (vacancy.getEmployer() != null) {
                companyView.setText(vacancy.getEmployer().getName());
            }
            if (vacancy.getArea() != null) {
                areaView.setText(vacancy.getArea().getName());
            }
            if (vacancy.getExperience() != null) {
                experienceView.setText(vacancy.getExperience().getName());
            }
            if (vacancy.getDescription() != null) {
                descriptionView.loadData(vacancy.getDescription(), "text/html; charset=utf-8", null);
            }
            if (vacancy.getSalary() != null) {
                String salaryString = "";
                if (vacancy.getSalary().getFrom() != null) {
                    salaryString += "от " + vacancy.getSalary().getFrom();
                }
                if (vacancy.getSalary().getTo() != null) {
                    salaryString += " до " + vacancy.getSalary().getTo();
                }
                if (vacancy.getSalary().getCurrency().equals("RUR")) {
                    salaryString += " руб.";
                }
                salaryView.setText(salaryString);
            }
        }
        findViewById(R.id.toolbar_progress).setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
