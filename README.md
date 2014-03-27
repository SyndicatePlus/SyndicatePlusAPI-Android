##SyndicatePlusApi-PHP

####Summary
The SyndicatePlus API is a platform that allows you to connect to the SyndicatePlus database for consumer product information. The main API is the SyndicatePlus Products API which allows you to search and get information on products, brands, categories and manufacturers.

####Api Access
For API access please visit the [SyndicatePlus Developer Portal][1] and [sign up][2] for an API key.

[1]: http://syndicateplus.com/developer-api/
[2]: http://syndicateplus.com/api-signup/

##Usage
Usage of the Products API is best illustrated by taking a look at the test source codes. To get started quickly, however, all you need to make a successful call to the SyndicatePlus API is the following code:

```
ProductsApi api = new ProductsApi(key,secret);
//Search products by name
List<Product> products = api.getProducts(ProductRequest.PRODUCT_NAME_PARAM,"name");
// Get a product by its EAN code
Product product = api.getProductByEan("8722700462989");

//Complex request on products
ProductRequest request = new ProductRequest();
request.addParam(ProductRequest.BRAND_ID_PARAM,"b942d46b-0e3a-4f26-b2ef-fe05d97759a7")
.sortBy(ProductRequest.NUTRION_ID_PARAM,"7587C402-8275-4881-BC3E-B21A32460E49")
.order(ResourceRequest.ORDER_DES)
.limit(10);
List<Product> products = api.getProducts(request);
```

For more info check javadocs and source codes.