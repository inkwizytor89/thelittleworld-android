package com.thelittleworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.thelittleworld.R;
import com.thelittleworld.core.AppCore;
import com.thelittleworld.entity.Item;
import com.thelittleworld.entity.ItemDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;

public class ItemsActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<>();
    public ArrayAdapter<String> arrayAdapter;
    public Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        buildArrayAdapter();
        printItems();
        onItemsButtonClick();
        onRefreshButtonClick();
    }

    private void buildArrayAdapter() {
        arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, list);
        ListView actionsListView = (ListView) findViewById(R.id.items_listView);
        actionsListView.setAdapter(arrayAdapter);
    }

    private void printItems() {
        List<Item> allItems = getAllItems();
        list.clear();
        list.addAll(itemsToString(allItems));
        arrayAdapter.notifyDataSetChanged();
    }

    private Collection<? extends String> itemsToString(List<Item> items) {
        List<String> result = new ArrayList<>();
        for (Item item : items) {
            String name = item.name;
            String type = item.type;
            String weight = item.weight.toString();
            result.add(type + "; " + name);
        }
        return result;
    }

    private List<Item> getAllItems() {
        final ItemDao itemDao = AppCore.getApplication().getDaoSession().getItemDao();
        return itemDao.loadAll();
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
        Log.i(APP_NAME, "Update klik");
        final Button button = (Button) findViewById(R.id.refresh_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list.add("counter " + (++counter));
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}
