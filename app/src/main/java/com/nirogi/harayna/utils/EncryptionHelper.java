package com.nirogi.harayna.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import android.util.Log;

public class EncryptionHelper {
    private static final String AES_ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "EDUNPS";

    public static String encrypt(String value)  {
        String valueToReturn="";
        try {
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(value.getBytes());
            valueToReturn=Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        }catch (Exception e)
        {
            Log.e(" Exception encrypt",""+e.getMessage());
        }

        return valueToReturn;
    }

    public static String decrypt(String encryptedValue)  {
        String valueToReturn="";
        try {
            SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), AES_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedValue, Base64.DEFAULT));
            valueToReturn= new String(decryptedBytes);
        }catch (Exception e)
        {
            Log.e(" Exception decrypt",""+e.getMessage());

        }
        return valueToReturn;

    }
}