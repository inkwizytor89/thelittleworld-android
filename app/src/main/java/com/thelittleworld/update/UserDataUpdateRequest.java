package com.thelittleworld.update;

import com.android.volley.Request;
import com.thelittleworld.entity.Companion;
import com.thelittleworld.entity.CompanionDao;
import com.thelittleworld.entity.Company;
import com.thelittleworld.entity.CompanyDao;
import com.thelittleworld.entity.User;
import com.thelittleworld.entity.UserDao;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDataUpdateRequest extends AbstractUpdateRequest {

    private static String URL = UPDATE_USER_DATA + "?user_id=1";

    public UserDataUpdateRequest() {
        super(Request.Method.GET, URL, null, new UserDataUpdateRespons());
    }

    private static class UserDataUpdateRespons extends AbstractResponseListener {

        @Override
        protected String getURL() {
            return URL;
        }

        @Override
        protected void execute(JSONObject response) throws JSONException {
            writeUserToDb(convertTo(response.getJSONObject("user"), User.class));
            writeCompanyToDb(convertTo(response.getJSONObject("company"), Company.class));
            writeCompanionsToDb(convertTo(response.getJSONArray("companions"), Companion[].class));
        }

        private void writeUserToDb(User user) {
            UserDao userDao = getDaoSession().getUserDao();
            userDao.deleteAll();
            userDao.insert(user);
        }

        private void writeCompanyToDb(Company company) {
            CompanyDao companyDao = getDaoSession().getCompanyDao();
            companyDao.deleteAll();
            companyDao.insert(company);
        }

        private void writeCompanionsToDb(Companion[] companions) {
            CompanionDao companionDao = getDaoSession().getCompanionDao();
            companionDao.deleteAll();
            for (Companion companion : companions) {
                companionDao.insert(companion);
            }
        }
    }
}
