package com.syndicateplus.lib.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseModel {

    public final static String FIELD_ID = "Id";
    public final static String FIELD_NAME = "Name";

    protected UUID mId;
    protected String mName;

    public BaseModel(JSONObject object) throws JSONException {
        mId = UUID.fromString(object.getString(FIELD_ID));
        mName = object.getString(FIELD_NAME);
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public static <T extends BaseModel> List<T> parseArray(JSONArray array,Class<T> cls) throws JSONException {
        List<T> result = new ArrayList<>();
        int n = array.length();
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor(JSONObject.class);
            for (int i=0; i<n; i++){
                result.add(constructor.newInstance(array.getJSONObject(i)));
            }
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {

        }
        return result;
    }
}
