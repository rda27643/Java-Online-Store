package onlinestore.model;

import onlinestore.exception.InvalidQuantityException;

public class OrderItem {
    private final Product product;
    private final int quantity;
    private final double price;

    public OrderItem(Product product, int quantity, double price) {
        if (product == null){
            throw new IllegalArgumentException("Product can not be null");
        }
        validateQuantity(quantity);
        validatePrice(price);
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void validateQuantity(int quantity){
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must greater than 0");
        }
    }

    public void validatePrice(double price){
        if (price <= 0){
            throw new IllegalArgumentException("Invalid price");
        }
    }

}
