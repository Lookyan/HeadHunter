package com.example.alex.headhunter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.alex.headhunter.R;
import com.example.alex.headhunter.activities.AuthActivity;
import com.example.alex.headhunter.utils.ThemeUtils;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_start);

        final ImageView splashImg = (ImageView) findViewById(R.id.imageSplash);

        splashImg.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, AuthActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
