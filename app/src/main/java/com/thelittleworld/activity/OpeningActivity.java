package com.thelittleworld.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thelittleworld.MainActivity;
import com.thelittleworld.R;
import com.thelittleworld.core.AppCore;
import com.thelittleworld.core.DbHelper;
import com.thelittleworld.entity.Item;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;
import static com.thelittleworld.core.AppCore.SERVER_URL;
import static com.thelittleworld.core.AppCore.UPDATE_ITEMS;

public class OpeningActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        dbHelper = AppCore.getInstance(getApplicationContext()).getDbHelper();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(1);

        callWriteItemsRequest();
    }

    private void callWriteItemsRequest() {
        AppCore.getInstance(this.getApplicationContext()).addToRequestQueue(new JsonArrayRequest(
                Request.Method.GET, SERVER_URL + UPDATE_ITEMS, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(APP_NAME, SERVER_URL + UPDATE_ITEMS + ": " + response.toString());
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<List<Item>>() {
                }.getType();
                writeItems((List<Item>) gson.fromJson(response.toString(), type));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO show information on Error
                Log.e("example", "Response: ");
            }
        }));
    }

    private void writeItems(List<Item> items) {
        SQLiteDatabase writeDb = dbHelper.getWritableDatabase();
        progressBar.setMax(items.size());

        for (Item item : items) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Item.COLUMN_NAME, item.name);
            contentValues.put(Item.COLUMN_DESCRIPTION, item.description);
            contentValues.put(Item.COLUMN_TYPE, item.type);
            contentValues.put(Item.COLUMN_WEIGHT, item.weight);
            writeDb.insert(Item.TABLE_NAME, null, contentValues);
        }
        writeDb.close();
        progressBar.setProgress(1);

        Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
