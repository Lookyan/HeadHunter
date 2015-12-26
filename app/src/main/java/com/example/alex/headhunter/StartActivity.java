package com.example.alex.headhunter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;

import com.example.alex.headhunter.models.Employer;

public class StartActivity extends NetBaseActivity {

    private int reqId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final ImageView splashImg = (ImageView) findViewById(R.id.imageSplash);

        splashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onServiceCallback(int requestId, int resultCode, Bundle data) {
        Employer employer = (Employer) data.getSerializable("employer");
        Toast.makeText(this, data.getString("haha"), Toast.LENGTH_SHORT).show();
    }
}
