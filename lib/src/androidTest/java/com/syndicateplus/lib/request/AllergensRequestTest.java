package com.syndicateplus.lib.request;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.TestHelper;
import com.syndicateplus.lib.model.Allergen;

import org.json.JSONArray;

public class AllergensRequestTest extends AndroidTestCase {

    public void testRequest() throws Exception{
        BaseRequest requestBody = new AllergensRequest();
        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(requestBody),
                TestHelper.DEFAULT_HANDLER
        );

        JSONArray data = new JSONArray(response);
        assertEquals(true,data.length() > 0);
        assertNotNull(new Allergen(data.getJSONObject(0)));
    }

}
