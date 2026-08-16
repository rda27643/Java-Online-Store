package onlinestore;


import onlinestore.model.Product;
import onlinestore.service.ProductService;
import onlinestore.util.InputUtils;

import java.util.Scanner;

public class Main {
    private final ProductService productService;
    private final Scanner scanner;

    private Main() {
        this.scanner = new Scanner(System.in);
        productService = new ProductService();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        boolean isRun = true;
        while (isRun) {
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
        System.out.println("Choice: ");
        int choice = InputUtils.readInt(scanner);
        switch (choice) {
            case 1 -> {
                addProduct();
                return true;
            }
            case 2 -> {
                showProducts();
                return true;
            }
            case 3 ->{
                removeProduct();
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
        System.out.print("Enter name of product: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = InputUtils.readDouble(scanner);
        System.out.print("Enter stock: ");
        int stock = InputUtils.readInt(scanner);
        try {
            Product product = new Product(name, price, stock);
            productService.addProduct(product);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showProducts(){
        int counter = 1;
        for (Product product : productService.getAllProducts()) {
            System.out.printf("%d- Product {\nid = #%d\nname = %s\nprice = $%,f\nstock = %d\n}\n",counter++,product.getId(),product
                    .getName(),product.getPrice() , product.getStock());
        }
    }

    private void removeProduct(){
        System.out.println("Enter ID product [1-2-...] : ");
        int productId = InputUtils.readInt(scanner);
        try {
            productService.removeProduct(productId);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
