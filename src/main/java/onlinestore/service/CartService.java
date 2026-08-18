package onlinestore.service;

import onlinestore.exception.*;
import onlinestore.model.Cart;
import onlinestore.model.CartItem;
import onlinestore.model.Product;
import onlinestore.util.ValidationUtils;

import java.util.List;

public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public CartService(Cart cart, ProductService productService) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart can not be null");
        }
        if (productService == null) {
            throw new IllegalArgumentException("Product service can not be null");
        }
        this.cart = cart;
        this.productService = productService;
    }

    public void addProductToCart(int productID, int quantity) {
        ValidationUtils.validateQuantity(quantity);
        Product product = productService.findProductById(productID);
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productID) {
                if ((item.getQuantity() + quantity) > product.getStock()) {
                    throw new InsufficientStockException("Insufficient stock");
                }
                item.increaseQuantity(quantity);
                return;

            }
        }
        cart.addItem(new CartItem(product, quantity));
    }

    public void removeProductFromCart(int productId) {
        ValidationUtils.validateProductID(productId);
        if (!cart.removeItem(productId)) {
            throw new ProductNotInCart("Product not in cart");
        }
    }

    public void updateQuantity(int productId, int quantity) {
        ValidationUtils.validateProductID(productId);
        ValidationUtils.validateQuantity(quantity);
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                if (item.getProduct().getStock() < quantity) {
                    throw new InsufficientStockException("Insufficient stock");
                }
                item.setQuantity(quantity);
                return;

            }
        }
        throw new ProductNotInCart("Product not in cart");
    }

    public Cart getCartView() {
        List<CartItem> items = cart.getItems();
        Cart cart_copy = new Cart();
        for (CartItem item : items) {
            cart_copy.addItem(item);
        }
        return cart_copy;
    }

    public void clearCart() {
        this.cart.clear();
    }
    public boolean isInCart(int productId){
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId){
                return true;
            }
        }
        return false;
    }
}
