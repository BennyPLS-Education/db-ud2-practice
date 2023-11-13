package userinterface;

import orm.Warehouse;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class Warehauses {
    public static void add() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        var name = input.nextLine();
        var region = getNumber("Region id: ");

        new Warehouse(0, name, region).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);
        var id = getNumber("Warehouse id: ");

        var warehouse = Warehouse.get(id);

        if (warehouse == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("1. Name");
        System.out.println("2. Region");
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    var name = input.nextLine();
                    new Warehouse(id, name, warehouse.region()).update();
                    return;
                }
                case 2 -> {
                    var region = getNumber("Region id: ");
                    new Warehouse(id, warehouse.name(), region).update();
                    return;
                }
                default -> System.out.println("Option Not valid");
            }
        }
    }

    public static void delete() {
        var id = getNumber("Warehouse id: ");
        var warehouse = Warehouse.get(id);

        if (warehouse == null) {
            System.out.println("Warehouse not found");
            return;
        }

        warehouse.delete();
    }

    public static void show() {
        Arrays.stream(Warehouse.getAll()).forEach(System.out::println);
    }

    public static void menu() {
        showMenu("Warehouses");

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
