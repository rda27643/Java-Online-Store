package onlinestore.service;

import onlinestore.exception.InsufficientStockException;
import onlinestore.exception.InvalidAmountStockException;
import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.Product;
import onlinestore.util.ValidationUtils;

import java.util.*;

public class ProductService {
    private final Map<Integer, Product> products;

    public ProductService() {
        this.products = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        ValidationUtils.validateProductNotNull(product);
        if (products.containsKey(product.getId())){
            throw new IllegalArgumentException("Product already exist");
        }
        products.put(product.getId(), product);
    }

    public void removeProduct(int productId) {
        ValidationUtils.validateProductID(productId);
        if (products.remove(productId) == null) {
            throw new ProductNotFoundException("Product not found");
        }

    }

    public Product findProductById(int productId) {
        ValidationUtils.validateProductID(productId);
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        return product;
    }

    public List<Product> getAllProducts() {
        if (products.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return new ArrayList<>(products.values());
    }

    public void increaseStock(int productId, int amount) {
        ValidationUtils.validateProductID(productId);
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        Product product = findProductById(productId);
        product.increaseStock(amount);
    }

    public void decreaseStock(int productId, int amount) {
        ValidationUtils.validateProductID(productId);
        if (amount <= 0) {
            throw new InvalidAmountStockException("Amount must be greater than 0");
        }
        Product product = findProductById(productId);
        if (!product.decreaseStock(amount)) {
            throw new InsufficientStockException("Insufficient stock");
        }

    }
}
