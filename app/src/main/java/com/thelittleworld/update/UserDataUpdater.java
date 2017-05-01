package com.thelittleworld.update;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thelittleworld.entity.User;

import org.json.JSONObject;

import java.lang.reflect.Type;

import static com.thelittleworld.core.AppCore.APP_NAME;

// TODO: remove
public class UserDataUpdater extends AbstractUpdater {

    @Override
    protected String getURL() {
        return UPDATE_USER_DATA + "?user_id=1";
    }

    @Override
    protected JsonRequest createRequest() {
        return new JsonObjectRequest(
                Request.Method.GET, getURL(), null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i(APP_NAME, getURL() + ": " + response.toString());
                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<User>() {
                }.getType();
                writeUser(gson.<User>fromJson(response.toString(), type));
            }

            private void writeUser(User user) {
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(APP_NAME, getURL() + ": " + error.toString());
            }
        });
    }
}
