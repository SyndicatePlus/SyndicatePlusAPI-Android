package com.syndicateplus.lib.request;

import com.syndicateplus.lib.request.BaseRequest;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

public class RequestBuilder {

    private String mKey;
    private String mSecret;

    public RequestBuilder(String key,String secret){
        mKey = key;
        mSecret = secret;
    }

    public HttpRequestBase buildRequest(BaseRequest request){
        HttpRequestBase result = null;

        switch (request.getMethod()){
            case GET:
                result = new HttpGet();
                break;
            case POST:
                result = new HttpPost();
                break;
            case PUT:
                result = new HttpPut();
                break;
            case DELETE:
                result = new HttpDelete();
                break;
        }

        result.addHeader("Content-type","application/json");
        result.addHeader("Authorization", request.getSignature(mKey, mSecret));
        result.setURI(URI.create(request.getUrl()));

        return result;
    }

}
