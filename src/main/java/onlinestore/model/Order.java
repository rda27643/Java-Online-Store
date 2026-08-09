package onlinestore.model;

import java.util.List;

public class Order {
    private final int id;
    private OrderStatus status;
    private final List<OrderStatus> items;
    private static int nextID = 1000;

    public Order(OrderStatus status, List<OrderStatus> items) {
        this.id = nextID++;
        this.status = status;
        this.items = items;
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

    public List<OrderStatus> getItems() {
        return items;
    }
}
