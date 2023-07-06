package com.nirogi.harayna.utils;

import android.app.Application;
import android.content.SharedPreferences;

public class NIROGI extends Application {

    public static NIROGI _instance;
    private SharedPreferences _sharedPReferences;
    // Get a Realm instance for this thread
    public static String token="eyJGaXJzdG5hbWUiOiJUZXN0IiwiTGFzdG5hbWUiOiJVc2VyIiwiRGlzdHJpY3QiOiJBbWJhbGEiLCJGYWNpbGl0eVR5cGUiOiIgRENIICIsIkZhY2lsaXR5IjoiIEFtYmFsYSAiLCJSb2xlIjoiVVNFUiIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiJUZXN0VXNlcksiLCJleHAiOjE2ODg2Nzc1NzksImlhdCI6MTY4ODY0MTU3OX0.ghw4oWz5FzBm_QOTiokrqifO5q2ccuFVerTztEKUUD1s-RSXsCGWng4Xx7CaCqXKcNtSle_ZWT4Ko8QggwRDrA";

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
