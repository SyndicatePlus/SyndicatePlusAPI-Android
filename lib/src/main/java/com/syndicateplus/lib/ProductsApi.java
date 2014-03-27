package com.syndicateplus.lib;

import com.syndicateplus.lib.model.Allergen;
import com.syndicateplus.lib.model.BaseModel;
import com.syndicateplus.lib.model.Brand;
import com.syndicateplus.lib.model.Manufacturer;
import com.syndicateplus.lib.model.Nutrion;
import com.syndicateplus.lib.model.Product;
import com.syndicateplus.lib.request.AllergensRequest;
import com.syndicateplus.lib.request.BaseRequest;
import com.syndicateplus.lib.request.BrandRequest;
import com.syndicateplus.lib.request.ManufacturerRequest;
import com.syndicateplus.lib.request.NutriensRequest;
import com.syndicateplus.lib.request.ProductRequest;
import com.syndicateplus.lib.request.RequestBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent Product API, HTTP request backed with DefaultHttpClient
 */
public class ProductsApi {

    private String mKey;
    private String mSecret;

    private RequestBuilder mRequestBuilder;
    private HttpClient mClient;
    private ResponseHandler<String> mHandler = new ResponseHandler<String>() {
        @Override
        public String handleResponse(final HttpResponse response)
                throws HttpResponseException, IOException {
            StatusLine statusLine = response.getStatusLine();
            switch (statusLine.getStatusCode()){
                case 401:
                    throw new HttpResponseException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase() + ". Please check your key and secret.");
                case 403:
                    throw new HttpResponseException(statusLine.getStatusCode(),
                            statusLine.getReasonPhrase() + ". Rate limit reached.");
                case 404:
                    return "null";
                default:
                    if (statusLine.getStatusCode() >= 300) {
                        throw new HttpResponseException(statusLine.getStatusCode(),
                                statusLine.getReasonPhrase());
                    }
                    HttpEntity entity = response.getEntity();
                    return entity == null ? null : EntityUtils.toString(entity);
            }
        }
    };

    public ProductsApi(String key,String secret){
        mKey = key;
        mSecret = secret;
        mRequestBuilder = new RequestBuilder(key,secret);
        mClient = new DefaultHttpClient();
    }

    private String executeRequest(BaseRequest request) throws IOException {
        return mClient.execute(mRequestBuilder.buildRequest(request),mHandler);
    }

    private <T extends BaseModel> T parseResult(String content, Class<T> cls){
        T result = null;
        if (content.equals("null"))
            return null;
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor(JSONObject.class);
            result = constructor.newInstance(new JSONObject(content));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {

        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private <T extends BaseModel> List<T> parseResults(String content,Class<T> cls){
        try {
            return BaseModel.parseArray(new JSONArray(content),cls);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get nutrions
     * @return List of Nutrions
     * @throws IOException
     */
    public List<Nutrion> getNutrions() throws IOException {
        NutriensRequest request = new NutriensRequest();
        return parseResults(executeRequest(request),Nutrion.class);
    }

    /**
     * Get allergens
     * @return List of Allergens
     * @throws IOException
     */
    public List<Allergen> getAllergens() throws IOException {
        AllergensRequest request = new AllergensRequest();
        return parseResults(executeRequest(request),Allergen.class);
    }

    /**
     * Return single product or null
     * @param id A unqiue id used to identify a product e.g.: “420d6006-a171-4217-8155-af13c848bbbd” for Good Noodles Saté.
     * @return Product
     * @throws IOException
     */
    public Product getProductById(String id) throws IOException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.byId(id);
        return parseResult(executeRequest(productRequest),Product.class);
    }

    /**
     * Return single product or null
     * @param ean The international article number to identify a product e.g.: 8711200189106 for Good Noodles Saté.
     * @return Product
     * @throws IOException
     */
    public Product getProductByEAN(String ean) throws IOException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.byEan(ean);
        return parseResult(executeRequest(productRequest),Product.class);
    }

    /**
     * Return filtered list of products
     * @param key Param key @see ProductRequest
     * @param value Param value
     * @return List of Products
     * @throws IOException
     */
    public List<Product> getProducts(String key, String value) throws IOException {
        ProductRequest productRequest = new ProductRequest();
        productRequest.addParam(key,value);
        return parseResults(executeRequest(productRequest), Product.class);
    }

    /**
     * Return filtered list of products
     * @param request Request to execute
     * @return List of products
     * @throws IOException
     */
    public List<Product> getProducts(ProductRequest request) throws IOException {
        if (request.isSingle()){
            List<Product> products = new ArrayList<>();
            products.add(parseResult(executeRequest(request),Product.class));
            return products;
        } else {
            return parseResults(executeRequest(request), Product.class);
        }
    }

    /**
     * Returns list of Brands
     * @param name An approximation of the brand name e.g.: hei for Heineken.
     * @return List of Brand
     * @throws IOException
     */
    public List<Brand> getBrands(String name) throws IOException {
        return getBrands(name,25,0);
    }

    /**
     * Returns list of Brands
     * @param name An approximation of the brand name e.g.: hei for Heineken.
     * @param limit The number of results to return. If not specified the default value of 25 is used. A maximum value of 100 is applied.
     * @param offset The offset of the first item in the result set. Use this in conjunction with Limit to request multiple pages of results.
     * @return List of Brand
     * @throws IOException
     */
    public List<Brand> getBrands(String name,int limit, int offset) throws IOException {
        BrandRequest brandRequest = new BrandRequest();
        brandRequest.addParam(BrandRequest.NAME_KEY,name);
        brandRequest.limit(limit).offset(offset);
        return parseResults(executeRequest(brandRequest),Brand.class);
    }

    /**
     * Returns Brand by Id
     * @param id A unqiue id used to identify a brand e.g.: “b942d46b-0e3a-4f26-b2ef-fe05d97759a7″ for Knorr.
     * @return Brand
     * @throws IOException
     */
    public Brand getBrandById(String id) throws IOException {
        BrandRequest request = new BrandRequest();
        request.byId(id);
        return parseResult(executeRequest(request),Brand.class);
    }

    /**
     * Return Manufacturer by Id
     * @param id A unique id used to identify a manufacturer e.g.: “9ec7e172-c2f8-461e-8197-0e0a26abb647″ for Segafredo.
     * @return Manufacturer
     * @throws IOException
     */
    public Manufacturer getManufacturerById(String id) throws IOException {
        ManufacturerRequest request = new ManufacturerRequest();
        request.byId(id);
        return parseResult(executeRequest(request),Manufacturer.class);
    }

    /**
     * Returns list of Manufactures
     * @param name An approximation of the manufacturer name e.g.: danone for Danone Nederland BV
     * @return List of Manufactures
     * @throws IOException
     */
    public List<Manufacturer> getManufactures(String name) throws IOException {
        return getManufactures(name,25,0);
    }

    /**
     * Returns list of Manufactures
     * @param name An approximation of the manufacturer name e.g.: danone for Danone Nederland BV
     * @param limit The number of results to return. If not specified the default value of 25 is used. A maximum value of 100 is applied.
     * @param offset The offset of the first item in the result set. Use this in conjunction with Limit to request multiple pages of results.
     * @return List of Manufactures
     * @throws IOException
     */
    public List<Manufacturer> getManufactures(String name,int limit,int offset) throws IOException {
        ManufacturerRequest request = new ManufacturerRequest();
        if (name != null){
            request.addParam(ManufacturerRequest.NAME_KEY,name);
        }
        request.limit(limit).offset(offset);
        return parseResults(executeRequest(request),Manufacturer.class);
    }

}
