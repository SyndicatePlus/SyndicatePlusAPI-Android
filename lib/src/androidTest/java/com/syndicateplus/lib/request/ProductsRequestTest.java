package com.syndicateplus.lib.request;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.TestHelper;
import com.syndicateplus.lib.model.BaseModel;
import com.syndicateplus.lib.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ProductsRequestTest extends AndroidTestCase {

    public static final String PRODUCT_ID = "420d6006-a171-4217-8155-af13c848bbbd";
    public static final String PRODUCT_EAN = "8711200189106";
    public static final String PRODUCT_NAME = "Good Noodles SatÃ©";

    public void testById() throws Exception{
        ProductRequest request = new ProductRequest();
        request.byId(PRODUCT_ID);
        checkProductResponse(request);
    }

    public void testByEan() throws Exception{
        ProductRequest request = new ProductRequest();
        request.byEan(PRODUCT_EAN);
        checkProductResponse(request);
    }

    private void checkProductResponse(BaseRequest request) throws IOException, JSONException {
        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        Product product = new Product(new JSONObject(response));
        assertEquals(PRODUCT_NAME,product.getName());
        assertEquals(PRODUCT_ID,product.getId().toString());
    }

    public void testSearch() throws Exception{
        ProductRequest request = new ProductRequest();
        request.addParam(ProductRequest.PRODUCT_NAME_PARAM, PRODUCT_NAME);

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        List<Product> products = BaseModel.parseArray(new JSONArray(response), Product.class);
        assertEquals(true,products.size() > 0);

        Product product = products.get(0);
        assertEquals(PRODUCT_NAME,product.getName());
        assertEquals(PRODUCT_ID,product.getId().toString());
    }

    public void testSearchWithParams() throws Exception{
        ProductRequest request = new ProductRequest();
        request.addParam(ProductRequest.BRAND_ID_PARAM,"b942d46b-0e3a-4f26-b2ef-fe05d97759a7")
               .sortBy(ProductRequest.NUTRION_ID_PARAM,"7587C402-8275-4881-BC3E-B21A32460E49")
               .order(ResourceRequest.ORDER_DES)
               .limit(10);

        String response = TestHelper.HTTP_CLIENT.execute(
                TestHelper.REQUEST_BUILDER.buildRequest(request),
                TestHelper.DEFAULT_HANDLER
        );

        List<Product> products = BaseModel.parseArray(new JSONArray(response), Product.class);
        assertEquals(true,products.size() > 0);
    }
}
