package onlinestore.model;

public class Product {
    private final int id;
    private String name;
    private double price;
    private int stock;

    private static int nextId = 1;

    public Product(String name, double price, int stock) {
        this.id = nextId++;
        setName(name);
        setPrice(price);
        setStock(stock);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        } else
            this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        } else
            this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("stock can not be negative");
        } else
            this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean increaseStock(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.stock += amount;
        return true;

    }

    public boolean decreaseStock(int amount) {
        if (amount <= 0 || amount>stock) {
            return false;
        }
            this.stock -= amount;
            return true;

    }

    @Override
    public String toString() {
        return "id= " + id +
                "\nname= " + name +
                "\nprice= " + price +
                "\nstock= " + stock;
    }
}
