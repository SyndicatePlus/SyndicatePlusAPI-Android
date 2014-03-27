package com.syndicateplus.lib.request;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.TestHelper;
import com.syndicateplus.lib.model.BaseModel;
import com.syndicateplus.lib.model.Brand;
import com.syndicateplus.lib.model.Manufacturer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ManufacturerRequestTest extends AndroidTestCase {


    public void testManufactureRequestWithParam() throws Exception{
        ManufacturerRequest request = new ManufacturerRequest();
        request.addParam(BrandRequest.NAME_KEY,"mar");
        request.limit(10);

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        List<Manufacturer> manufacturers = BaseModel.parseArray(new JSONArray(response),Manufacturer.class);
        assertEquals(true,manufacturers.size() > 0);
    }

    public void testById() throws Exception{
        ManufacturerRequest request = new ManufacturerRequest();
        request.byId("9ec7e172-c2f8-461e-8197-0e0a26abb647");

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        Manufacturer manufacturer = new Manufacturer(new JSONObject(response));
        assertEquals("Segafredo",manufacturer.getName());
    }

}
