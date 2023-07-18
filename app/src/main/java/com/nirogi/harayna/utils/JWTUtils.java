package com.nirogi.harayna.utils;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JWTUtils {

    public static ArrayList<JSONObject> parseJWT(String token)  {
        ArrayList<JSONObject> objectArrayList= new ArrayList<>();
        try {
            String[] parts = token.split("\\.");
            String header = parts[0];
            String payload = parts[1];

            String decodedHeader = new String(Base64.decode(header, Base64.DEFAULT));
            String decodedPayload = new String(Base64.decode(payload, Base64.DEFAULT));

            JSONObject decodedHeaderJson = new JSONObject(decodedHeader);
            JSONObject decodedPayloadJson = new JSONObject(decodedPayload);
            objectArrayList.add(decodedHeaderJson);
            objectArrayList.add(decodedPayloadJson);
        }catch (Exception e)
        {
            Log.e(" Exception ",""+e.getMessage());
        }

        return objectArrayList;
    }
}