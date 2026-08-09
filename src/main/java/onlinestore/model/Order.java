package onlinestore.model;

import java.util.List;

public class Order {
    private final int id;
    private OrderStatus status;
    private final List<OrderItem> items;
    private static int nextID = 1000;

    public Order(OrderStatus status, List<OrderItem> items) {
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

    public List<OrderItem> getItems() {
        return items;
    }
    public double getTotalPrice(){
        double sum =0;
        for (OrderItem item : items) {
            sum+=item.getTotalPrice();
        }
        return sum;
    }
}
