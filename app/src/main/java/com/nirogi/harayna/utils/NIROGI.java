package com.nirogi.harayna.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class NIROGI extends Application {

    public static NIROGI _instance;
    private SharedPreferences _sharedPReferences;
    // Get a Realm instance for this thread

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;
        _sharedPReferences = getApplicationContext().getSharedPreferences(SharedParams.PREFERENCE, MODE_PRIVATE);


    }
    public static synchronized NIROGI getInstance() {
        return _instance;
    }
    public SharedPreferences getPreferences() {
        return _sharedPReferences;
    }
}
