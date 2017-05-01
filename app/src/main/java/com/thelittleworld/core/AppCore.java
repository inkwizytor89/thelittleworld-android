package com.thelittleworld.core;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.thelittleworld.entity.DaoMaster;
import com.thelittleworld.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.io.File;

public class AppCore extends Application {

    final public static String APP_NAME = "The Little World";
    final public static String SERVER_URL = "http://10.0.2.2:8080";

    public static final boolean ENCRYPTED = false;

    private static AppCore instance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : DbHelper.DATABASE_NAME);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        createRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    private void createRequestQueue() {
        if (getCacheDirectory() != null) {

            // Instantiate the cache
            Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

            // Set up the network to use HttpURLConnection as the HTTP client.
            Network network = new BasicNetwork(new HurlStack());

            // Instantiate the RequestQueue with the cache and network.
            mRequestQueue = new RequestQueue(cache, network);

            // Start the queue
            mRequestQueue.start();
        }
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    private File getCacheDirectory() {
        return null;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static AppCore getApplication() {
        return instance;
    }
}