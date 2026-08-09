package onlinestore.service;

import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
        private Map<Integer, Product> products;

    public ProductService() {
        this.products = new HashMap<>();
    }
    public void addProduct(Product product){
        products.putIfAbsent(product.getId(),product);
    }
    public void removeProduct(int productId){
        if (products.remove(productId) == null){
            throw new ProductNotFoundException("Product not found");
        }
    }
    public Product findProductById(int productId){
        if (products.get(productId) == null){
            throw new ProductNotFoundException("Product not found");
        } else
            return products.get(productId);
    }
    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Integer, Product> productEntry : products.entrySet()) {
            productList.add(productEntry.getValue());
        }
        return productList;
    }
}
