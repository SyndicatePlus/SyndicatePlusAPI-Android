package com.syndicateplus.lib;

import com.syndicateplus.lib.request.RequestBuilder;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by lex .
 */
public class TestHelper {
    public static final String KEY = "OB13ZgiwtENRYWq4vp0n84lzZgesQYEXyf0xEBWTvqoc5tGCTO9ow8ykSOhnXHlFCcPe-vmzxDYxk9UZIAS7ETW3hZz-FMoDiPjsr4gHWvhrC-crdt95HRKAGeJsgBLhFPl-JGbEQKPcVwJhifnUOcv8Mg7iuOR7vYvij2nxsW8=";
    public static final String SECRET = "f-e9k_zM58KdJTwv8gU2r5PLA_J7wvewzPIF35y_EAG7QpMMDu2lCgLa8m7Sd7WVCWCUEw2cB_8AG68-2rF8EWWpxGMj6jDJse59iY6gQzrmOeiL82pecX_COg07Yu64hpbb81d7uWFY7vT4X-sDRnMGaPjsl6r4oy1GlkPsOE8=";
    public static final RequestBuilder REQUEST_BUILDER = new RequestBuilder(KEY,SECRET);
    public static final BasicResponseHandler DEFAULT_HANDLER = new BasicResponseHandler();
    public static final HttpClient HTTP_CLIENT = new DefaultHttpClient();
}
