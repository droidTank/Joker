package com.pushpal.jokinggraphically;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokeDisplay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke);

        jokeDisplay = findViewById(R.id.tv_joke);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String joke = extras.getString("joke", "Hey it's a bad joke!");
            jokeDisplay.setText(joke);
        }
    }
}
