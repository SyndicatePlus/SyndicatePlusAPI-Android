package com.syndicateplus.lib.util;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.TestHelper;

import java.util.UUID;

/**
 * Created by lex .
 */
public class UtilsTest extends AndroidTestCase {

    private static final String EXPECTED_RESULT = "AujqgdYG+xmaxwJfS76nkhHQwxI=";
    private static final String EXPECTED_RESULT_WITH_EMPTY = "NaVbc4lFYoOU0Mw1wDDR8RgUoKk=";

    public void testSha1Base64(){
        UUID nonce = UUID.fromString("060346e3-4569-4e64-83c4-4fd37787f5dc");
        long timestamp = 1394213573;
        String target = TestHelper.SECRET + "GET" + "http://api.syndicateplus.com/v1/products/product" + "ean=8711200189106" + nonce.toString() + String.valueOf(timestamp);
        String result = Utils.sha1base64(target);
        assertEquals(EXPECTED_RESULT,result);
    }


    public void testSha1Base64WithEmptyParams(){
        UUID nonce = UUID.fromString("7b2d51ad-a4ac-4ee6-80f2-c9a060eab425");
        long timestamp = 1394217236;
        String target = TestHelper.SECRET + "GET" + "http://api.syndicateplus.com/v1/nutrients" + "" + nonce.toString() + String.valueOf(timestamp);
        String result = Utils.sha1base64(target);
        assertEquals(EXPECTED_RESULT_WITH_EMPTY,result);
    }

}
