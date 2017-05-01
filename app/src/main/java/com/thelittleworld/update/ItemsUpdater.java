package com.thelittleworld.update;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thelittleworld.entity.Item;
import com.thelittleworld.entity.ItemDao;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import static com.thelittleworld.core.AppCore.APP_NAME;

// TODO: 01.05.2017 change to request class like UserDataUpdateRequest
public class ItemsUpdater extends AbstractUpdater {

    @Override
    protected String getURL() {
        return UPDATE_ITEMS;
    }

    @Override
    protected JsonRequest createRequest() {
        return new JsonArrayRequest(
                Request.Method.GET, getURL(), null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i(APP_NAME, getURL() + ": " + response.toString());
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<List<Item>>() {
                }.getType();
                writeItems(gson.<List<Item>>fromJson(response.toString(), type));
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(APP_NAME, getURL() + ": " + error.toString());
            }
        });
    }

    private void writeItems(List<Item> items) {
        ItemDao itemDao = getDaoSession().getItemDao();
        itemDao.deleteAll();

        for (Item item : items) {
            itemDao.insert(item);
        }
    }

}
