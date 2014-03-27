package com.syndicateplus.lib.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lex .
 */
public class Utils {

    public static String sha1base64(String message){
        String result = null;
        try {
            result = Base64.encodeToString(MessageDigest.getInstance("SHA-1").digest(message.getBytes("UTF-8")), Base64.DEFAULT);
            result = result.substring(0,result.length() - 1);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ignored) {

        }
        return result;
    }

}
