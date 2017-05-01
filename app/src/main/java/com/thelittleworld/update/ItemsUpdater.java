package com.thelittleworld.update;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thelittleworld.entity.Item;
import com.thelittleworld.entity.ItemDao;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;
import static com.thelittleworld.core.AppCore.SERVER_URL;
import static com.thelittleworld.core.AppCore.getApplication;

public class ItemsUpdater {

    final public static String UPDATE_ITEMS = "/update_items";
    private Boolean isComplete = false;


    public ItemsUpdater() {
        getApplication().addToRequestQueue(createDownloadItemRequest());
    }

    private JsonArrayRequest createDownloadItemRequest() {
        return new JsonArrayRequest(
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
                Log.e(APP_NAME, error.toString());
            }
        });
    }

    private void writeItems(List<Item> items) {
        ItemDao itemDao = getApplication().getDaoSession().getItemDao();
        itemDao.deleteAll();

        for (Item item : items) {
            itemDao.insert(item);
        }

        isComplete = true;
    }

    public Boolean getComplete() {
        return isComplete;
    }
}
