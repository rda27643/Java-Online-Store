package onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private OrderStatus status;
    private final List<OrderItem> items;
    private static int nextID = 1000;

    public Order(List<OrderItem> items) {
        if (items == null){
            throw new IllegalArgumentException("Invalid order items");
        }
        this.id = nextID++;
        this.items = new ArrayList<>(items);
        status = OrderStatus.PENDING;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
}
