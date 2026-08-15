package onlinestore;


import onlinestore.model.Product;
import onlinestore.service.ProductService;
import onlinestore.util.InputUtils;

import java.util.Scanner;

public class Main {
    private static ProductService productService;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        productService = new ProductService();
        while (true) {
            displayMenu();
            handleChoice(sc);
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

    private static void handleChoice(Scanner sc) {
        System.out.println("Choice: ");
        int choice = InputUtils.readInt(sc);
        switch (choice) {
            case 1 -> {
            }
            case 2 -> {
            }
            case 3 -> {
            }
            case 4 -> {
            }
            case 5 -> {
            }
            case 6 -> {
            }
            case 7 -> {
            }
            case 8 -> {
            }
            case 9 -> {
            }
            case 10 -> {
            }
            case 11 -> {
            }
            case 12 -> {
            }
            case 0 -> {
            }
            default -> {
                System.out.println("Please enter the valid number");
            }
        }

    }

    public static void addProduct(Scanner sc) {
        System.out.print("Enter name of product: ");
        String name = sc.nextLine();
        System.out.print("Enter price");
        double price = InputUtils.readDouble(sc);
        System.out.print("Enter stock");
        int stock = InputUtils.readInt(sc);
        try {
            Product product = new Product(name, price, stock);
            productService.addProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
