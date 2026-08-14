package onlinestore.service;

import onlinestore.exception.*;
import onlinestore.model.Cart;
import onlinestore.model.CartItem;
import onlinestore.model.Product;
import onlinestore.util.InputUtils;

import java.util.List;

public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public CartService(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    public void addProductToCart(int productID, int quantity) {
        Product product = productService.findProductById(productID);
        InputUtils.validateQuantity(quantity);
        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Insufficient stock");
        }
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productID) {
                if ((item.getQuantity() + quantity) > item.getProduct().getStock()) {
                    throw new InsufficientStockException("Insufficient stock");
                } else {
                    item.increaseQuantity(quantity);
                    return;
                }
            }
        }
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem);
    }

    public void removeProductFromCart(int productId) {
        InputUtils.validateProductID(productId);
        if (!cart.removeItem(productId)) {
            throw new ProductNotInCart("Product not in cart");
        }
    }

    public void updateQuantity(int productId, int quantity) {
        InputUtils.validateProductID(productId);
        InputUtils.validateQuantity(quantity);
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                if (item.getProduct().getStock() < quantity) {
                    throw new InsufficientStockException("Insufficient stock");
                } else {
                    item.setQuantity(quantity);
                    return;
                }
            }
        }
        throw new ProductNotInCart("Product not in cart");
    }

    public Cart getCart() {
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
}
