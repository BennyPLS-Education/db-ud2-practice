import userinterface.*;

import static utils.Inputs.getNumber;

public class Main {
    public static void main(String[] args) {
        int option;

        System.out.println("-------------------------");
        System.out.println("    SELECT AN ACTION:    ");
        System.out.println("-------------------------");
        System.out.println("0. Exit");
        System.out.println("1. Menu Products");
        System.out.println("2. Menu Producers");
        System.out.println("3. Menu Regions");
        System.out.println("4. Menu Warehouses");
        System.out.println("5. Menu Clients");
        System.out.println("6. Menu Orders");
        System.out.println("7. Menu Warehouse Products");
        System.out.println("-------------------------");

        option = getNumber("Option: ");

        switch (option) {
            case 0 -> System.out.print("");

            case 1 -> Products.menu();
            case 2 -> Producers.menu();
            case 3 -> Regions.menu();
            case 4 -> Warehauses.menu();
            case 5 -> Clients.menu();
            case 6 -> Orders.menu();
            case 7 -> WarehousesProducts.menu();
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) main(args);
    }
}
