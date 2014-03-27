package com.syndicateplus.lib.request;

public class ProductRequest extends ResourceRequest {

    /**
     * A unique id used to identify a manufacturer e.g.: “3462dcaf-4915-4c8e-bf8a-f901ae8caa8d” for H.J. Heinz BV.
     */
    public static final String MANUFACTURE_ID_PARAM = "manufacturerid";
    /**
     * A unqiue id used to identify a brand e.g.: “d183ade5-8b09-4b89-9066-c6433622ed8d” for Heineken.
     */
    public static final String PRODUCT_NAME_PARAM = "productname";
    /**
     * An approximation of the product name e.g.: ‘sweet chili’ for Sweet Chili Sauce.
     */
    public static final String BRAND_ID_PARAM = "brandid";

    /**
     * Nutrion param , used for order
     * @see <a href="http://info.syndicateplus.com/api-products/">Products api</a>
     */
    public static final String NUTRION_ID_PARAM = "nutrientid";

    {
        mSingleResource = "product";
        mResourceName = "products";
    }

    public void byEan(String ean){
        if (mIsCreated || mIsLocked){
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        mRequestParams.put("ean",ean);
        mIsLocked = true;
        mIsCreated = true;
        mIsSingle = true;
    }

}
