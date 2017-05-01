package com.thelittleworld.update;

import com.android.volley.Request;
import com.thelittleworld.entity.User;
import com.thelittleworld.entity.UserDao;

import org.json.JSONObject;

public class UserDataUpdateRequest extends AbstractUpdateRequest {

    private static String URL = UPDATE_USER_DATA + "?user_id=1";

    public UserDataUpdateRequest() {
        super(Request.Method.GET, URL, null, new UserDataRespons());
    }

    private static class UserDataRespons extends AbstractResponseListener {

        @Override
        protected String getURL() {
            return URL;
        }

        @Override
        protected void execute(JSONObject response) {
            writeUserToDb(convertTo(response, User.class));
        }

        private void writeUserToDb(User user) {
            UserDao userDao = getDaoSession().getUserDao();
            userDao.deleteAll();
            userDao.insert(user);
        }
    }
}
