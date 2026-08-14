package onlinestore.service;

import onlinestore.exception.EmptyCartException;
import onlinestore.exception.OrderNotFoundException;
import onlinestore.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final Map<Integer, Order> orders;
    private final Cart cart;
    private final ProductService productService;

    public OrderService(Cart cart, ProductService productService) {
        this.orders = new HashMap<>();
        this.cart = cart;
        this.productService = productService;
    }

    public Order createOrder() {
        if (this.cart.isEmpty()) {
            throw new EmptyCartException("Cart is empty");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            orderItems.add(new OrderItem(item.getProduct(), item.getQuantity(), item.getProduct().getPrice()));
            productService.decreaseStock(item.getProduct().getId(), item.getQuantity());
        }
        cart.clear();
        Order order = new Order(orderItems);
        orders.put(order.getId(), order);
        return order;

    }

    public Order getOrderById(int orderId) {
        if (orders.get(orderId) == null) {
            throw new OrderNotFoundException("Order not found");
        } else
            return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        for (Map.Entry<Integer, Order> orderEntry : orders.entrySet()) {
            orderList.add(orderEntry.getValue());
        }
        return orderList;
    }

    public void changeOrderStatus(int orderId, OrderStatus orderStatus) {
        Order order = getOrderById(orderId);
        switch (order.getStatus()) {
            case OrderStatus.PENDING -> {
                if ((orderStatus == OrderStatus.CONFIRMED) || (orderStatus == OrderStatus.CANCELLED)) {
                    order.setStatus(orderStatus);
                } else {
                    throw new IllegalArgumentException("Order status can not change");
                }
            }
            case OrderStatus.CONFIRMED -> {
                if ((orderStatus == OrderStatus.CANCELLED) || (orderStatus == OrderStatus.COMPLETED)) {
                    order.setStatus(orderStatus);
                } else {
                    throw new IllegalArgumentException("Order status can not change");
                }
            }
            case OrderStatus.CANCELLED, OrderStatus.COMPLETED -> {
                throw new IllegalArgumentException("Order status can not change");
            }
            default -> {
                throw new IllegalArgumentException("Invalid order status");
            }
        }

    }

}
