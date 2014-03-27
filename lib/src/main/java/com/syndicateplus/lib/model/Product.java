package com.syndicateplus.lib.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Product extends BaseModel {

    public static final String ALLERGENS_KEY = "Allergens";
    private List<Allergen> mAllergens;
    public static final String NUTRIENTS_KEY = "Nutrients";
    private List<Nutrion> mNutrients;
    public static final String BRAND_KEY = "Brand";
    private Brand mBrand;
    public static final String MANUFACTURER_KEY = "Manufacturer";
    private Manufacturer mManufacturer;
    public static final String IMAGE_URL_KEY = "ImageUrl";
    private String mImageUrl;
    public static final String INGREDIENTS_KEY = "Ingredients";
    private String mIngredients;
    public static final String DESCRIPTION_KEY = "Description";
    private String mDescription;

    public Product(JSONObject object) throws JSONException {
        super(object);
        
        mAllergens = BaseModel.parseArray(object.getJSONArray(ALLERGENS_KEY),Allergen.class);
        mNutrients = BaseModel.parseArray(object.getJSONArray(NUTRIENTS_KEY),Nutrion.class);
        mBrand = new Brand(object.getJSONObject(BRAND_KEY));
        mImageUrl = object.getString(IMAGE_URL_KEY);
        mIngredients = object.getString(INGREDIENTS_KEY);
        mDescription = object.getString(DESCRIPTION_KEY);
    }

    public List<Allergen> getAllergens() {
        return mAllergens;
    }

    public List<Nutrion> getNutrients() {
        return mNutrients;
    }

    public Brand getBrand() {
        return mBrand;
    }

    public Manufacturer getManufacturer() {
        return mManufacturer;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public String getDescription() {
        return mDescription;
    }
}

