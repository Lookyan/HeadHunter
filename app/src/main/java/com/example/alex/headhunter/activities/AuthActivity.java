package com.example.alex.headhunter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.alex.headhunter.R;
import com.example.alex.headhunter.utils.ThemeUtils;

/**
 * Created by nano on 26.12.15.
 */
public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_auth);


        final Button btn_skip = (Button) findViewById(R.id.btn_skip);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
