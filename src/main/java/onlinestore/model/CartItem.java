package onlinestore.model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int increase) {
        this.quantity += increase;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice(){
        return product.getPrice() * this.quantity;
    }
}
