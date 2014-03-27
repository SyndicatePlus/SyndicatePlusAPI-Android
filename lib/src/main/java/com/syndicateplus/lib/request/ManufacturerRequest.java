package com.syndicateplus.lib.request;

public class ManufacturerRequest extends ResourceRequest {

    /**
     * An approximation of the manufacturer name e.g.: danone for Danone Nederland BV
     */
    public static final String NAME_KEY = "name";

    {
        mSingleResource = "manufacturer";
        mResourceName = "manufacturers";
    }

    @Override
    public ResourceRequest order(String order) {
        return this;
    }

    @Override
    public ResourceRequest sortBy(String key) {
        return this;
    }
}
