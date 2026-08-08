package onlinestore.model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

}
