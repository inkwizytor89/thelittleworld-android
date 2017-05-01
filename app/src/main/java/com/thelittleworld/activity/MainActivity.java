package com.thelittleworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.thelittleworld.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<String>();
    public ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);
        ListView actionsListView = (ListView) findViewById(R.id.actions_listView);
        actionsListView.setAdapter(arrayAdapter);


        onItemsButtonClick();
        onAddEventButtonClick();
    }

    private void onItemsButtonClick() {
        final Button button = (Button) findViewById(R.id.items_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onAddEventButtonClick() {
        final Button button = (Button) findViewById(R.id.addEvent_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                klik();
            }
        });
    }

    private void klik() {
        list.add("Kocham Sylwie !!");
        arrayAdapter.notifyDataSetChanged();
    }
}
