package onlinestore.model;

import onlinestore.exception.InvalidQuantityException;
import onlinestore.util.InputUtils;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        InputUtils.validateQuantity(quantity);
        this.quantity = quantity;
        this.product = product;
    }

    public void setQuantity(int quantity) {
        InputUtils.validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void increaseQuantity(int increase) {
        if (increase <= 0) {
            throw new InvalidQuantityException("must be positive");
        } else
            this.quantity += increase;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * this.quantity;
    }


}
