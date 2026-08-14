package onlinestore.util;

import onlinestore.exception.InvalidProductIDException;
import onlinestore.exception.InvalidQuantityException;
import onlinestore.model.Product;

public class validationUtils {

    public static void validateProductID(int id) {
        if (id <= 0) {
            throw new InvalidProductIDException("Invalid product id");
        }
    }
    public static void validateQuantity(int quantity){
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must greater than 0");
        }
    }
    public static void validateProductNotNull(Product product){
        if (product == null){
            throw new IllegalArgumentException("Invalid product");
        }
    }
}
