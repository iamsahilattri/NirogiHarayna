package com.nirogi.harayna.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class NIROGI extends Application {

    public static NIROGI _instance;
    private SharedPreferences _sharedPReferences;
    // Get a Realm instance for this thread
    public static String token="eyJGaXJzdG5hbWUiOiJOaWtoaWwiLCJMYXN0bmFtZSI6IkJhbnNhbCIsIkRpc3RyaWN0IjoiQW1iYWxhIiwiRmFjaWxpdHlUeXBlIjoiUEhDIiwiRmFjaWxpdHkiOiJLZXNhcmkiLCJSb2xlIjoiVVNFUiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJwaGNrZXNyaSIsImV4cCI6MTY4ODc1MTAwNywiaWF0IjoxNjg4NzE1MDA3fQ.5DFrKatgXOJglan4k4R1vXOcEVhec94RkZs7-dsDNRleV5JeyxApMxR4Bhi3QmEA_CEaA7fA-Q7KjwYCzJp0OQ";

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
