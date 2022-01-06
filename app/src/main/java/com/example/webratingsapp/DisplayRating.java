package com.example.webratingsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayRating extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_rating);

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");

        TextView tv_url = findViewById(R.id.tv_url2);
        tv_url.setText("Rate from 0 to 5 : " + url);
    }

    public void resumeToMainActivity(View v) {

        EditText et_rate = findViewById(R.id.et_rate);
        String rate = et_rate.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("rate", rate);
        intent.putExtra("url", url);
        setResult(RESULT_OK, intent);
        finish();
    }
}