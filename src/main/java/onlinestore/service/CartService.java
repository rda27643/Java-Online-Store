package onlinestore.service;

import onlinestore.exception.InvalidQuantityException;
import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.Cart;
import onlinestore.model.CartItem;
import onlinestore.model.Product;

public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public CartService(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    public void addProductToCart(int productID, int quantity){
        if (quantity <= 0){
            throw new InvalidQuantityException("Quantity must greater than 0");
        }
        Product product = productService.findProductById(productID);
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productID){
                item.increaseQuantity(quantity);
                return;
            }
        }
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem);
    }
    public void removeProductFromCart(int productId){
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId){
                cart.removeItem(productId);
                return;
            }
        }
        throw new ProductNotFoundException("Product not in cart");
    }
}
