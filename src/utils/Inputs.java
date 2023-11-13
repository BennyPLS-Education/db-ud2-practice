package utils;

import orm.CustomerType;
import orm.Quality;
import orm.Status;

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

    public static Quality qualitySelector() {
        System.out.println("1. A");
        System.out.println("2. B");
        System.out.println("3. C");
        System.out.println("4. D");
        System.out.println("5. E");
        Quality selected;
        while (true) {
            selected = switch (getNumber("Select a quality: ")) {
                case 1 -> Quality.A;
                case 2 -> Quality.B;
                case 3 -> Quality.C;
                case 4 -> Quality.D;
                case 5 -> Quality.E;
                default -> null;
            };

            if (selected != null) break;

            System.out.println("Not a valid option");
        }

        return selected;
    }

    public static CustomerType customerTypeSelector() {
        System.out.println("1. Particular");
        System.out.println("2. Small business");
        System.out.println("3. Business");
        CustomerType selected;
        while (true) {
            selected = switch (getNumber("Select a customer type: ")) {
                case 1 -> CustomerType.PARTICULAR;
                case 2 -> CustomerType.SMALL_BUSINESS;
                case 3 -> CustomerType.BUSINESS;
                default -> null;
            };

            if (selected != null) break;

            System.out.println("Not a valid option");
        }

        return selected;
    }

    public static Status statusSelector() {
        System.out.println("1. Preparing");
        System.out.println("2. In shipping");
        System.out.println("3. Delivered");
        Status selected;
        while (true) {
            selected = switch (getNumber("Select a status: ")) {
                case 1 -> Status.PREPARING;
                case 2 -> Status.IN_SHIPPING;
                case 3 -> Status.DELIVERED;
                default -> null;
            };

            if (selected != null) break;

            System.out.println("Not a valid option");
        }

        return selected;
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
