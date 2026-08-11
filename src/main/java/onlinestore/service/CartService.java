package onlinestore.service;

import onlinestore.exception.InsufficientStockException;
import onlinestore.exception.InvalidProductIDException;
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

    public void addProductToCart(int productID, int quantity) {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must greater than 0");
        }
        if (productID <= 0) {
            throw new InvalidProductIDException("Invalid Id");
        }
        Product product = productService.findProductById(productID);
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
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                cart.removeItem(productId);
                return;
            }
        }
        throw new ProductNotFoundException("Product not in cart");
    }

    public void updateQuantity(int productId, int quantity) {
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId() == productId) {
                if (item.getProduct().getStock() < quantity) {
                    throw new InsufficientStockException("Insufficient stock");
                } else {
                    item.setQuantity(quantity);
                }
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}
