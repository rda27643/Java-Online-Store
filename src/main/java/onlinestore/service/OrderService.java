package onlinestore.service;

import onlinestore.exception.EmptyCartException;
import onlinestore.model.Cart;
import onlinestore.model.CartItem;
import onlinestore.model.Order;
import onlinestore.model.OrderItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final Map<Integer, Order> orders;
    private final Cart cart;
    private final ProductService productService;

    public OrderService() {
        orders = new HashMap<>();
        cart = new Cart();
        productService = new ProductService();
    }

    public void createOrder(){
        if (this.cart.isEmpty()){
            throw new EmptyCartException("Cart is empty");
        } else {
            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem item : cart.getItems()) {
                orderItems.add(new OrderItem(item.getProduct(), item.getQuantity(), item.getProduct().getPrice()));
                productService.decreaseStock(item.getProduct().getId(), item.getQuantity());
            }
            cart.clear();
            Order order = new Order(orderItems);
        }
    }

}
