package com.thelittleworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.thelittleworld.core.DbHelper;
import com.thelittleworld.entity.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;

public class ItemsActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<String>();
    public ArrayAdapter<String> arrayAdapter;
    public Integer counter = 0;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        dbHelper = new DbHelper(getApplicationContext());
        buildArrayAdapter();
        printItems();
        onItemsButtonClick();
        onRefreshButtonClick();
    }

    private void buildArrayAdapter() {
        arrayAdapter = new ArrayAdapter<String>
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
        List<String> result = new ArrayList<String>();
        for (Item item : items) {
            String name = item.name;
            String description = item.description;
            String type = item.type;
            String weight = item.weight.toString();
            result.add(type + "; " + name);
        }
        return result;
    }

    private List<Item> getAllItems() {

        SQLiteDatabase readDb = dbHelper.getReadableDatabase();
        String[] projection = {
                Item.COLUMN_ID,
                Item.COLUMN_NAME,
                Item.COLUMN_DESCRIPTION,
                Item.COLUMN_TYPE,
                Item.COLUMN_WEIGHT
        };

        Cursor cursor = readDb.rawQuery("select * from " + Item.TABLE_NAME, null);

        List<Item> itemList = new ArrayList<Item>();
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.id = cursor.getInt(cursor.getColumnIndexOrThrow(Item.COLUMN_ID));
            item.name = cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_NAME));
            item.description = cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_DESCRIPTION));
            item.type = cursor.getString(cursor.getColumnIndexOrThrow(Item.COLUMN_TYPE));
            item.weight = cursor.getDouble(cursor.getColumnIndexOrThrow(Item.COLUMN_WEIGHT));
            itemList.add(item);
        }
        cursor.close();
        readDb.close();
        return itemList;
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
