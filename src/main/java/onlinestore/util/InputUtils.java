package onlinestore.util;

import java.util.Scanner;

public class InputUtils {
    public static int readInt(Scanner scanner){
        String input = scanner.nextLine();
        if (input.trim().matches("[0-9]+")){
            return Integer.parseInt(input);
        } else {
            System.out.println("Enter number");
            return -1;
        }
    }
    public static double readDouble(Scanner scanner){
        String input = scanner.nextLine();
        if (input.trim().matches("[0-9]+")){
            return Double.parseDouble(input);
        } else {
            System.out.println("Enter number");
            return -1;
        }
    }
}
