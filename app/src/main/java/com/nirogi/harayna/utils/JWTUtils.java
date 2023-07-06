package com.nirogi.harayna.utils;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

public class JWTUtils {

    public static JSONObject parseJWT(String token) throws JSONException {
        String[] parts = token.split("\\.");
        String header = parts[0];
        String payload = parts[1];

        String decodedHeader = new String(Base64.decode(header, Base64.DEFAULT));
        String decodedPayload = new String(Base64.decode(payload, Base64.DEFAULT));

        JSONObject decodedHeaderJson = new JSONObject(decodedHeader);
        JSONObject decodedPayloadJson = new JSONObject(decodedPayload);

        return decodedHeaderJson;
    }
}