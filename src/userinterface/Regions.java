package userinterface;

import orm.Region;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class Regions {
    public static void add() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        var name = input.nextLine();

        var x = getNumber("X: ");

        var y = getNumber("Y: ");

        new Region(0, name, x, y).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var id = getNumber("Region id: ");

        var region = Region.get(id);

        if (region == null) {
            System.out.println("Region not found");
            return;
        }

        System.out.println("1. Name");
        System.out.println("2. X");
        System.out.println("3. Y");
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    var name = input.nextLine();
                    new Region(id, name, region.x(), region.y()).update();
                    return;
                }
                case 2 -> {
                    var x = getNumber("X: ");
                    new Region(id, region.name(), x, region.y()).update();
                    return;
                }
                case 3 -> {
                    var y = getNumber("Y: ");
                    new Region(id, region.name(), region.x(), y).update();
                    return;
                }
                default -> System.out.println("Option Not valid");
            }
        }
    }

    public static void delete() {
        var id = getNumber("Region id: ");
        var region = Region.get(id);

        if (region == null) {
            System.out.println("Region not found");
            return;
        }

        region.delete();
    }

    public static void show() {
        Arrays.stream(Region.getAll()).forEach(System.out::println);
    }

    public static void menu() {
        showMenu("Regions");

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
