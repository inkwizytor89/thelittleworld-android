package com.thelittleworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.thelittleworld.R;
import com.thelittleworld.update.ItemsUpdater;

public class OpeningActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(1);

        ItemsUpdater itemsUpdater = new ItemsUpdater();

        Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
