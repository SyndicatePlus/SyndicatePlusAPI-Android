package com.syndicateplus.lib;

import android.test.AndroidTestCase;

import com.syndicateplus.lib.model.Allergen;
import com.syndicateplus.lib.model.Brand;
import com.syndicateplus.lib.model.Manufacturer;
import com.syndicateplus.lib.model.Nutrion;
import com.syndicateplus.lib.model.Product;
import com.syndicateplus.lib.request.ProductRequest;
import com.syndicateplus.lib.request.ProductsRequestTest;

import java.util.List;

public class ProductsApiTest extends AndroidTestCase {

    private ProductsApi api = new ProductsApi(TestHelper.KEY, TestHelper.SECRET);

    public void testGetNutrions() throws Exception{
        List<Nutrion> nutrions = api.getNutrions();
        assertEquals(true,nutrions.size() > 0);
    }

    public void testGetAllergens() throws Exception{
        List<Allergen> allergens = api.getAllergens();
        assertEquals(true,allergens.size() > 0);
    }

    public void testGetProductById() throws Exception{
        Product product = api.getProductById(ProductsRequestTest.PRODUCT_ID);
        assertEquals(ProductsRequestTest.PRODUCT_NAME,product.getName());
    }

    public void testGetProductByEan() throws Exception{
        Product product = api.getProductByEAN(ProductsRequestTest.PRODUCT_EAN);
        assertEquals(ProductsRequestTest.PRODUCT_NAME,product.getName());
    }

    public void testGetProducts() throws Exception{
        List<Product> list = api.getProducts(ProductRequest.PRODUCT_NAME_PARAM,ProductsRequestTest.PRODUCT_NAME);
        assertEquals(true,list.size() > 0);
    }

    public void testGetProductsByRequest() throws Exception{
        ProductRequest productRequest = new ProductRequest();
        productRequest.addParam(ProductRequest.BRAND_ID_PARAM,"b942d46b-0e3a-4f26-b2ef-fe05d97759a7");
        List<Product> list = api.getProducts(productRequest);
        assertEquals(true,list.size() > 0);
    }

    public void testGetBrands() throws Exception{
        List<Brand> list = api.getBrands("hein");
        assertEquals(true,list.size() > 0);
    }

    public void testGetManufatures() throws Exception{
        List<Manufacturer> list = api.getManufactures("mar");
        assertEquals(true,list.size() > 0);
    }

    public void testGetBrandById() throws Exception{
        Brand brand = api.getBrandById("b942d46b-0e3a-4f26-b2ef-fe05d97759a7");
        assertEquals("Knorr",brand.getName());
    }

    public void testGetManufacturerById() throws Exception{
        Manufacturer manufacturer = api.getManufacturerById("9ec7e172-c2f8-461e-8197-0e0a26abb647");
        assertEquals("Segafredo",manufacturer.getName());
    }

    public void testNullProduct() throws Exception{
        Product product = api.getProductById("b942d46b-0e3a-4f26-b2ef-fe05d97759a7");
        assertNull(product);
    }
}
