package onlinestore.service;

import onlinestore.exception.InsufficientStockException;
import onlinestore.exception.InvalidProductIDException;
import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.Product;
import onlinestore.util.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Map<Integer, Product> products;

    public ProductService() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        InputUtils.validateProductNotNull(product);
        if (products.putIfAbsent(product.getId(), product) != null){
            throw new IllegalArgumentException("Product already exist");
        }
    }

    public void removeProduct(int productId) {
        InputUtils.validateProductID(productId);
        if (products.remove(productId) == null) {
            throw new ProductNotFoundException("Product not found");
        }
    }

    public Product findProductById(int productId) {
        InputUtils.validateProductID(productId);
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void increaseStock(int productId, int amount) {
        InputUtils.validateProductID(productId);
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        product.increaseStock(amount);
    }

    public void decreaseStock(int productId, int amount) {
        InputUtils.validateProductID(productId);
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        if (!product.decreaseStock(amount)) {
            throw new InsufficientStockException("Insufficient stock");
        }

    }
}
