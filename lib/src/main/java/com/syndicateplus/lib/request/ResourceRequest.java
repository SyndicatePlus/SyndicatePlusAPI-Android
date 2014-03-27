package com.syndicateplus.lib.request;

public abstract class ResourceRequest extends BaseRequest {

    {
        mMethod = HttpMethod.GET;
    }

    protected String mId;
    protected String mSingleResource;
    protected boolean mIsCreated = false;
    protected boolean mIsLocked = false;
    protected int mParamsCount = 0;

    protected static final String ILLEGAL_STATE_EXCEPTION = "Request params already set";
    protected static final String LIMIT_EXCEPTION = "Limit must be between 1 and 100";
    protected static final String SORT_KEY = "sort";
    protected static final String ORDER_KEY = "order";
    protected static final String LIMIT_KEY = "limit";
    protected static final String OFFSET_KEY = "offset";

    public static final String ORDER_ASC = "ascending";
    public static final String ORDER_DES = "descending";

    protected void checkLocked(){
        if (mIsLocked){
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }
    }

    public ResourceRequest addParam(String key,String value){
        checkLocked();
        mIsCreated = true;
        String paramKey = mParamsCount > 0 ? "q" + String.valueOf(mParamsCount) : "q";
        mRequestParams.put(paramKey,key + ":" + value);
        mParamsCount++;
        return this;
    }

    public ResourceRequest order(String order){
        checkLocked();
        mRequestParams.put(ORDER_KEY,order);
        mIsCreated = true;
        return this;
    }

    /**
     * Set limit of results, default 25
     * @param limit Limit of result, max 100
     * @return Request object
     */
    public ResourceRequest limit(int limit){
        checkLocked();
        if (limit > 100 || limit <= 0){
            throw new IllegalArgumentException(LIMIT_EXCEPTION);
        }
        mRequestParams.put(LIMIT_KEY,String.valueOf(limit));
        mIsCreated = true;
        return this;
    }

    public ResourceRequest offset(int offset){
        checkLocked();
        mRequestParams.put(OFFSET_KEY,String.valueOf(offset));
        mIsCreated = true;
        return this;
    }

    public ResourceRequest sortBy(String key){
        checkLocked();
        mRequestParams.put(SORT_KEY,key);
        mIsCreated = true;
        return this;
    }

    public ResourceRequest sortBy(String key,String value){
        return sortBy(key + ":" + value);
    }


    public void byId(String id){
        if (mIsCreated || mIsLocked){
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }
        mIsLocked = true;
        mIsCreated = true;
        mIsSingle = true;
        mId = id;
    }


    @Override
    public String getUrlWithoutParams() {
        String base = super.getUrlWithoutParams();
        if (mIsSingle){
            base = base + "/" + mSingleResource;
        }

        if (mId != null){
            return base + "/" + mId;
        } else {
            return base;
        }
    }
}
