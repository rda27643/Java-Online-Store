package onlinestore.model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0){
            throw new IllegalArgumentException("must be positive");
        } else
            this.quantity = quantity;
    }

    public void increaseQuantity(int increase) {
        if (quantity < 0){
            throw new IllegalArgumentException("must be positive");
        } else
            this.quantity += quantity;
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
