package com.thelittleworld.update;

import com.thelittleworld.entity.Item;
import com.thelittleworld.entity.ItemDao;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemsUpdateRequest extends AbstractUpdateRequest {

    private static String URL = UPDATE_ITEMS;

    public ItemsUpdateRequest() {
        super(URL, null, new ItemsUpdateRespons());
    }

    private static class ItemsUpdateRespons extends AbstractResponseListener {

        private static final String ITEMS_CONTENER = "items";

        @Override
        protected String getURL() {
            return URL;
        }

        @Override
        protected void execute(JSONObject response) throws JSONException {
            writeItemsToDb(convertTo(response.getJSONArray(ITEMS_CONTENER), Item[].class));
        }

        private void writeItemsToDb(Item[] items) {
            ItemDao itemDao = getDaoSession().getItemDao();
            itemDao.deleteAll();
            for (final Item item : items) {
                itemDao.insert(item);
            }
        }
    }
}
