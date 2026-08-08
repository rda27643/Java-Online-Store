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
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name can not be empty");
        } else
            this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("price can not be less than 0");
        } else
            this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 1) {
            throw new IllegalArgumentException("stock can not be less than 1");
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

    @Override
    public String toString() {
        return "id= " + id +
                "\nname= " + name +
                "\nprice= " + price +
                "\nstock= " + stock;
    }
}
