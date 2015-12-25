package com.example.alex.headhunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.alex.headhunter.models.Employer;

public class StartActivity extends NetBaseActivity {

    private int reqId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.this.getServiceHelper().getEmployerInfo(1455);
            }
        });
    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {
        Employer employer = (Employer) data.getSerializable("employer");
        Toast.makeText(this, data.getString("haha"), Toast.LENGTH_SHORT).show();
    }
}
