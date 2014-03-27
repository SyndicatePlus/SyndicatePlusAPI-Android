package com.syndicateplus.lib.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Manufacturer extends BaseModel {

    public static final String WEBSITE_KEY = "Website";
    private String mWebsite;
    public static final String DESCRIPTION_KEY = "Description";
    private String mDescription;

    public Manufacturer(JSONObject object) throws JSONException {
        super(object);

        if (object.has(WEBSITE_KEY)){
            mWebsite = object.isNull(WEBSITE_KEY) ? null : object.getString(WEBSITE_KEY);
            mDescription = object.isNull(DESCRIPTION_KEY) ? null : object.getString(DESCRIPTION_KEY);
        }
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getDescription() {
        return mDescription;
    }
}
