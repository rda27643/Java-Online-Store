package onlinestore;


import onlinestore.exception.InsufficientStockException;
import onlinestore.exception.InvalidProductIDException;
import onlinestore.exception.InvalidQuantityException;
import onlinestore.exception.ProductNotInCart;
import onlinestore.model.Cart;
import onlinestore.model.Product;
import onlinestore.service.CartService;
import onlinestore.service.ProductService;
import onlinestore.util.ConsoleReader;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final ProductService productService;
    private final Cart cart;
    private final CartService cartService;
    private final Scanner scanner;

    private Main() {
        this.scanner = new Scanner(System.in);
        productService = new ProductService();
        cart = new Cart();
        cartService = new CartService(cart, productService);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        boolean isRun = true;
        while (isRun) {
            System.out.println();
            displayMenu();
            isRun = handleChoice();
        }
    }

    private static void displayMenu() {
        System.out.println("===================");
        System.out.println("\tOnline Store");
        System.out.println("===================");
        System.out.println("""
                 1- Add Product
                 2- Show Products
                 3- Remove Product
                \s
                 4- Add Product To Cart
                 5- Remove Product From Cart
                 6- Update Cart Quantity
                 7- Show Cart
                 8- Clear Cart
                \s
                 9- Create Order
                 10- Find Order
                 11- Show All Orders
                 12- Change Order Status
                \s
                 0- Exit
                """);
    }

    private boolean handleChoice() {
        int choice = ConsoleReader.readInt("Choice: ");
        switch (choice) {
            case 1 -> {
                addProduct();
                return true;
            }
            case 2 -> {
                showProducts();
                return true;
            }
            case 3 -> {
                removeProduct();
                return true;
            }
            case 4 -> {
                addProductToCart();
                return true;
            }

            case 0 -> {
                System.out.println("Exiting.....");
                return false;
            }

            default -> {
                System.out.println("Please enter the valid number");
                return true;
            }
        }

    }

    private void addProduct() {
        String name = ConsoleReader.readString("Enter name of product: ");
        double price = ConsoleReader.readPositiveDouble("Enter price: ");
        int stock = ConsoleReader.readPositiveInt("Enter stock: ");
        try {
            Product product = new Product(name, price, stock);
            productService.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showProducts() {
        int counter = 1;
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Not products found");
        }
        for (Product product : productService.getAllProducts()) {
            System.out.printf("%d- Product {\nid = #%d\nname = %s\nprice = $%,f\nstock = %d\n}\n", counter++, product.getId(), product
                    .getName(), product.getPrice(), product.getStock());
        }
    }

    private void removeProduct() {
        int productId = ConsoleReader.readPositiveInt("Enter ID product : ");
        try {
            productService.removeProduct(productId);
        } catch (IllegalArgumentException | InvalidProductIDException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProductToCart() {
        int productId = ConsoleReader.readInt("Enter ID product: ");
        int quantity = ConsoleReader.readInt("Enter quantity product: ");
        try {
            cartService.addProductToCart(productId, quantity);
        } catch (InsufficientStockException | InvalidQuantityException e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeProductFromCart() {
        int productId = ConsoleReader.readInt("Enter ID product: ");
        try {

            cartService.removeProductFromCart(productId);
        } catch (InvalidProductIDException | ProductNotInCart e) {
            System.out.println(e.getMessage());
        }
    }
}
