package onlinestore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem cartItem) {
        if (cartItem == null){
            throw new IllegalArgumentException("Cart item can not be null");
        }
        items.add(cartItem);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean removeItem(int productId) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalPrice() {
        double sumPrice = 0;
        for (CartItem item : items) {
            sumPrice += item.getTotalPrice();
        }
        return sumPrice;
    }

    public int getItemCount() {
        int sumCount = 0;
        for (CartItem item : items) {
            sumCount += item.getQuantity();
        }
        return sumCount;
    }
}
