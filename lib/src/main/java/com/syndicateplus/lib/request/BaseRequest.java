package com.syndicateplus.lib.request;

import android.os.PatternMatcher;
import android.util.Base64;

import com.syndicateplus.lib.util.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseRequest {



    protected HttpMethod mMethod = HttpMethod.GET;
    protected String mResourceName;
    protected Map<String,String> mRequestParams;
    protected boolean mIsSingle = false;

    public final static String BASE_URL = "http://api.syndicateplus.com/";
    public final static int VERSION = 1;

    protected final static String SING_FORMAT = "Key=\"%s\",Timestamp=\"%s\",Nonce=\"%s\",Signature=\"%s\"";
    protected static final Pattern PATTERN = Pattern.compile("(%[0-9A-F]{2})");


    protected BaseRequest() {
        mRequestParams = new TreeMap<>();
    }

    public boolean isSingle() {
        return mIsSingle;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public String getSignature(String key,String secret){
        UUID nonce = UUID.randomUUID();
        long time = System.currentTimeMillis() / 1000;
        String content = secret + mMethod.toString() + getUrlWithoutParams() + prepareQueryString()
                + nonce.toString() + String.valueOf(time);

        String result = Utils.sha1base64(content);
        result = String.format(SING_FORMAT,key,String.valueOf(time),nonce.toString(),result);
        return result;
    }

    public String getUrlWithoutParams(){
        return BASE_URL + "v" + String.valueOf(VERSION) + "/" + mResourceName;
    }

    public String getUrl(){
        String params = prepareQueryString();
        if (!params.equals("")){
            return getUrlWithoutParams() + "?" + prepareQueryString();
        } else
            return getUrlWithoutParams();
    }

    private String prepareQueryString(){
        if (mRequestParams.size() == 0)
            return "";

        StringBuilder result = new StringBuilder();
        for (String key : mRequestParams.keySet()){
            try {
                result.append(URLEncoder.encode(key,"UTF-8"))
                        .append("=");

                String param = mRequestParams.get(key);
                result.append(URLEncoder.encode(param,"UTF-8"));
                result.append("&");
            } catch (UnsupportedEncodingException ignored) {
            }
        }

        result.deleteCharAt(result.length() - 1);

        String resultString = result.toString();
        Matcher matcher = PATTERN.matcher(resultString);
        if (matcher.find()){
            do {
                result.replace(matcher.start(),matcher.end(),matcher.group().toLowerCase());
            } while (matcher.find() && ! matcher.hitEnd());
        }
        return result.toString().replace("+","%20");
    }

}
