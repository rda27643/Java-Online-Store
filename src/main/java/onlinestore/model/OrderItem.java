package onlinestore.model;

public class OrderItem {
    private final Product product;
    private final int quantity;
    private final double price;

    public OrderItem(Product product, int quantity, double price) {
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
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }
}
