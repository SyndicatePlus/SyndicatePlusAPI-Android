package com.syndicateplus.lib.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Nutrion extends BaseModel {

    protected static final String UNITS_KEY = "Units";
    protected static final String VALUE_KEY = "Value";

    protected String mUnit;
    protected double mValue;

    public Nutrion(JSONObject object) throws JSONException {
        super(object);
        if (object.has(UNITS_KEY)){
            mUnit = object.getString(UNITS_KEY);
            mValue = object.getDouble(VALUE_KEY);
        }
    }

    public String getUnit() {
        return mUnit;
    }

    public double getValue() {
        return mValue;
    }

}
