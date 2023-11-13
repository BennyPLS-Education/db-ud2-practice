package userinterface;

import orm.WarehouseProduct;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class WarehousesProducts {
    public static void add() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        var quantity = getNumber("Quantity: ");

        new WarehouseProduct(warehouse, product, quantity);
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");

        var warehouseProduct = WarehouseProduct.get(warehouse, product);

        if (warehouseProduct == null) {
            System.out.println("Warehouse product not found");
            return;
        }

        var quantity = getNumber("Quantity: ");

        new WarehouseProduct(warehouse, product, quantity).update();
    }

    public static void delete() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");

        var warehouseProduct = WarehouseProduct.get(warehouse, product);

        if (warehouseProduct == null) {
            System.out.println("Warehouse product not found");
            return;
        }

        warehouseProduct.delete();
    }

    public static void show() {
        Arrays.stream(WarehouseProduct.getAll()).forEach(System.out::println);
    }

    public static void menu() {
        showMenu("Warehouse Product");

        var option = getNumber("Select an option: ");

        switch (option) {
            case 0 -> System.out.print("");
            case 1 -> show();
            case 2 -> add();
            case 3 -> update();
            case 4 -> delete();
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) menu();
    }
}
