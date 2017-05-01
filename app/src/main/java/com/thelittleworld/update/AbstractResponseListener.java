package com.thelittleworld.update;

import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.thelittleworld.entity.DaoSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

import static com.thelittleworld.core.AppCore.APP_NAME;
import static com.thelittleworld.core.AppCore.getApplication;

abstract class AbstractResponseListener implements Response.Listener<JSONObject> {

    private final DaoSession daoSession;

    AbstractResponseListener() {
        daoSession = getApplication().getDaoSession();
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.i(APP_NAME, getURL() + ": " + response.toString());
        try {
            execute(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getURL();

    protected abstract void execute(JSONObject response) throws JSONException;

    <T> T convertTo(JSONObject response, Class<T> classOfT) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                }).create();
        return gson.fromJson(response.toString(), classOfT);
    }

    <T> T convertTo(JSONArray response, Class<T> classOfT) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                }).create();
        return gson.fromJson(response.toString(), classOfT);
    }

    DaoSession getDaoSession() {
        return daoSession;
    }
}
