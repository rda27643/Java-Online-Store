package onlinestore.util;

import onlinestore.exception.InvalidProductIDException;
import onlinestore.exception.InvalidQuantityException;

public class InputUtils {

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
}
