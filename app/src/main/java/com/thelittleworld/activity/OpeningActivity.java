package com.thelittleworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.thelittleworld.R;
import com.thelittleworld.core.AppCore;
import com.thelittleworld.update.ItemsUpdateRequest;
import com.thelittleworld.update.UserDataUpdateRequest;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(1);

        AppCore.getApplication().addToRequestQueue(new ItemsUpdateRequest());
        AppCore.getApplication().addToRequestQueue(new UserDataUpdateRequest());


        Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
