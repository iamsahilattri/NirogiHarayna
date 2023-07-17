package com.nirogi.harayna.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class NIROGI extends Application {

    public static NIROGI _instance;
    private SharedPreferences _sharedPReferences;
    // Get a Realm instance for this thread
    public static String token="eyJGaXJzdG5hbWUiOiJOaWtoaWwiLCJMYXN0bmFtZSI6IkJhbnNhbCIsIkRpc3RyaWN0IjoiQW1iYWxhIiwiRmFjaWxpdHlUeXBlIjoiUEhDIiwiRmFjaWxpdHkiOiJLZXNhcmkiLCJSb2xlIjoiVVNFUiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJwaGNrZXNyaSIsImV4cCI6MTY4OTYyODY2MiwiaWF0IjoxNjg5NTkyNjYyfQ.GHzOsefBs-66jaAFXu-kEZggtlwgSLLZM45lRjJ48yr0sUBR06Vq63t-aL-XqsVthgxvas9bg-0MahB-Yb9QUg";

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
