package com.thelittleworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.thelittleworld.core.AppCore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;
import static com.thelittleworld.core.AppCore.SERVER_URL;
import static com.thelittleworld.core.AppCore.UPDATE_ITEMS;

public class ItemsActivity extends AppCompatActivity {

    public List<String> list = new ArrayList<String>();
    public ArrayAdapter<String> arrayAdapter;
    public Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, list);
        ListView actionsListView = (ListView) findViewById(R.id.items_listView);
        actionsListView.setAdapter(arrayAdapter);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, SERVER_URL + UPDATE_ITEMS, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(APP_NAME, SERVER_URL + UPDATE_ITEMS + ": " + response.toString());
                        try {
                            printItems(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("example", "Response: ");
                    }
                });

        // Access the RequestQueue through your singleton class.
        AppCore.getInstance(this.getApplicationContext()).addToRequestQueue(jsObjRequest);

        onItemsButtonClick();
        onRefreshButtonClick();
    }

    private void printItems(JSONArray items) throws JSONException {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            result.add(item.getString("type") + "; " + item.getString("name"));
        }
        list.clear();
        list.addAll(result);
        arrayAdapter.notifyDataSetChanged();
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
