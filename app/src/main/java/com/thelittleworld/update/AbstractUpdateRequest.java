package com.thelittleworld.update;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import static com.thelittleworld.core.AppCore.APP_NAME;

public abstract class AbstractUpdateRequest extends JsonObjectRequest {

    final protected static String SERVER_URL = "http://10.0.2.2:8080";

    final protected static String UPDATE_USER_DATA = SERVER_URL + "/update_user_data";
    final protected static String UPDATE_ITEMS = SERVER_URL + "/update_items";

    public AbstractUpdateRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener) {
        super(url, jsonRequest, listener, createErrorListener(url));
    }

    public AbstractUpdateRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener) {
        super(method, url, jsonRequest, listener, createErrorListener(url));
    }


    private static Response.ErrorListener createErrorListener(final String url) {
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(APP_NAME, url + ": " + error.toString());
            }
        };
    }
}
