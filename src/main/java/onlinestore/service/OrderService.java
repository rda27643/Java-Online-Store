package onlinestore.service;

import onlinestore.exception.EmptyCartException;
import onlinestore.exception.InsufficientStockException;
import onlinestore.exception.OrderNotFoundException;
import onlinestore.model.Cart;
import onlinestore.model.CartItem;
import onlinestore.model.Order;
import onlinestore.model.OrderItem;
import onlinestore.model.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final Map<Integer, Order> orders;
    private final Cart cart;
    private final ProductService productService;

    public OrderService(Cart cart, ProductService productService) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart can not be null");
        }

        if (productService == null) {
            throw new IllegalArgumentException("ProductService can not be null");
        }

        this.orders = new HashMap<>();
        this.cart = cart;
        this.productService = productService;
    }

    public Order createOrder() {
        if (this.cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProduct().getStock()) {
                throw new InsufficientStockException("Insufficient stock for product: " + item.getProduct().getId());
            }
        }

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            orderItems.add(new OrderItem(item.getProduct(), item.getQuantity(), item.getProduct().getPrice()));
        }
        for (CartItem item : cart.getItems()) {
            productService.decreaseStock(item.getProduct().getId(), item.getQuantity());
        }
        Order order = new Order(orderItems);
        orders.put(order.getId(), order);
        cart.clear();
        return order;

    }

    public Order getOrderById(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void changeOrderStatus(int orderId, OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Order status can not be null");
        }

        Order order = getOrderById(orderId);
        OrderStatus currentStatus = order.getStatus();

        switch (currentStatus) {
            case OrderStatus.PENDING -> {
                if (newStatus != OrderStatus.CONFIRMED && newStatus != OrderStatus.CANCELLED) {
                    throw new IllegalArgumentException("Invalid order status transition");
                }
            }
            case OrderStatus.CONFIRMED -> {
                if (newStatus != OrderStatus.CANCELLED && newStatus != OrderStatus.COMPLETED) {
                    throw new IllegalArgumentException("Invalid order status transition");
                }
            }
            case OrderStatus.CANCELLED, OrderStatus.COMPLETED -> {
                throw new IllegalArgumentException("Order status can not change");
            }
        }
        order.setStatus(newStatus);

    }

}
