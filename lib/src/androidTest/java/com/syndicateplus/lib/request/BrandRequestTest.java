package com.syndicateplus.lib.request;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.TestHelper;
import com.syndicateplus.lib.model.BaseModel;
import com.syndicateplus.lib.model.Brand;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BrandRequestTest extends AndroidTestCase {

    public void testBrandRequestWithParam() throws Exception{
        BrandRequest request = new BrandRequest();
        request.addParam(BrandRequest.NAME_KEY,"hein");
        request.limit(10);

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        List<Brand> brandList = BaseModel.parseArray(new JSONArray(response),Brand.class);
        assertEquals(true,brandList.size() > 0);
    }

    public void testById() throws Exception{
        BrandRequest request = new BrandRequest();
        request.byId("b942d46b-0e3a-4f26-b2ef-fe05d97759a7");

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        Brand brand = new Brand(new JSONObject(response));
        assertEquals("Knorr",brand.getName());
    }


}
