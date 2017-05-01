package com.thelittleworld.update;

import com.android.volley.toolbox.JsonRequest;
import com.thelittleworld.entity.DaoSession;

import static com.thelittleworld.core.AppCore.getApplication;

// TODO: 01.05.2017 remove
abstract class AbstractUpdater {

    final protected static String SERVER_URL = "http://10.0.2.2:8080";

    final protected static String UPDATE_USER_DATA = SERVER_URL + "/update_user_data";
    final protected static String UPDATE_ITEMS = SERVER_URL + "/update_items";

    private final DaoSession daoSession;

    public AbstractUpdater() {
        daoSession = getApplication().getDaoSession();
        runRequest();
    }

    private void runRequest() {
        getApplication().addToRequestQueue(createRequest());
    }

    protected abstract String getURL();

    protected abstract JsonRequest createRequest();

    protected DaoSession getDaoSession() {
        return daoSession;
    }
}
