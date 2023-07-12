package com.nirogi.harayna.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class NIROGI extends Application {

    public static NIROGI _instance;
    private SharedPreferences _sharedPReferences;
    // Get a Realm instance for this thread
    public static String token="eyJGaXJzdG5hbWUiOiJOaWtoaWwiLCJMYXN0bmFtZSI6IkJhbnNhbCIsIkRpc3RyaWN0IjoiQW1iYWxhIiwiRmFjaWxpdHlUeXBlIjoiUEhDIiwiRmFjaWxpdHkiOiJLZXNhcmkiLCJSb2xlIjoiVVNFUiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJwaGNrZXNyaSIsImV4cCI6MTY4OTE4OTk0NCwiaWF0IjoxNjg5MTUzOTQ0fQ.zM4mDmHD4VIjkf5pbdHTEvyMupKbzPtQEeQ5jovAr6yVq7BppuXkg1IM26XTVUtZeayX4lzCk-gFh3oObYUpgQ";

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
