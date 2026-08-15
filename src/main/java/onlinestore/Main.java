package onlinestore;


public class Main {
    public static void main(String[] args) {
    }


    public static void displayMenu(){
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
}
