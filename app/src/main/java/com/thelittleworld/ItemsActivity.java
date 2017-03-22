package com.thelittleworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<String>();
    public ArrayAdapter<String> arrayAdapter;
    public Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
//
//        list.add("1");
//        list.add("2");
//        list.add("3");

        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);
        ListView actionsListView = (ListView) findViewById(R.id.items_listView);
        actionsListView.setAdapter(arrayAdapter);


        onItemsButtonClick();
        onRefreshButtonClick();
    }

    private void onItemsButtonClick() {
        final Button button = (Button) findViewById(R.id.items_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ItemsActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onRefreshButtonClick() {
        final Button button = (Button) findViewById(R.id.refresh_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.add("counter " + (++counter));
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
