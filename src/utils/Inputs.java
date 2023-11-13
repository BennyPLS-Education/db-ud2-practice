package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {
    public static int getNumber(String message) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            try {
                System.out.print(message);
                return input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
                System.out.println("Not a valid number");
            }
        }
    }
    
    public static String getString(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextLine();
    }
    
    public static void showMenu(String name) {
        System.out.println("-------------------------");
        System.out.println('\t' + name);
        System.out.println("-------------------------");
        System.out.println("0. Back");
        System.out.println("1. Show");
        System.out.println("2. Add");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("-------------------------");
    }
}
