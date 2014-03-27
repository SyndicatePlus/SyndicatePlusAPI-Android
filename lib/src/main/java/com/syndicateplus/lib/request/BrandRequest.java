package com.syndicateplus.lib.request;

public class BrandRequest extends ResourceRequest{

    /**
     * A unqiue id used to identify a brand e.g.: “b942d46b-0e3a-4f26-b2ef-fe05d97759a7″ for Knorr.
     */
    public static final String NAME_KEY = "name";

    {
        mSingleResource = "brand";
        mResourceName = "brands";
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
