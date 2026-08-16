package onlinestore.util;

import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleReader() {
    }
    public static int readInt(String message){
        while(true){
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Enter a valid Integer");
            }
        }
    }

    public static int readPositiveInt(String message) {
        while (true) {
            int input = readInt(message);
            if (input > 0){
                return input;
            }
            System.out.println("Input must be greater than 0");
        }
    }

    public static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            }
        }
    }
    public static double readPositiveDouble(String message){
        while (true){
            double input = readDouble(message);
            if (input > 0){
                return input;
            }
            System.out.println("Number mus be grater than 0");
        }
    }

    public static String readString(String message) {
        while (true) {
            System.out.print(message);
            String input = SCANNER.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input can not be empty");

        }
    }


}
