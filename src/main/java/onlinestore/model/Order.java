package onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private OrderStatus status;
    private final List<OrderItem> items;
    private static int nextId = 1000;

    public Order(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.items = new ArrayList<>(items);
        status = OrderStatus.PENDING;
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalPrice() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public void setStatus(OrderStatus status) {
        if (status != null) {
            this.status = status;
        }
    }
}
