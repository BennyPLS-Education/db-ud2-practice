package userinterface;

import orm.Product;
import orm.Region;
import orm.Warehouse;

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

        if (new Region(0, name, x, y).insert()) System.out.println("Region added");
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
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    final var name = input.nextLine();
                    result = new Region(id, name, region.x(), region.y()).update();
                }
                case 2 -> {
                    var x = getNumber("X: ");
                    result = new Region(id, region.name(), x, region.y()).update();
                }
                case 3 -> {
                    var y = getNumber("Y: ");
                    result = new Region(id, region.name(), region.x(), y).update();
                }
                default -> System.out.println("Option not valid");
            }
            if (result != null) {
                if (result) System.out.println("Region updated");
                break;
            }
        }
    }
    
    public static void clear() {
        var regions = Region.getAll();
        
        if (regions == null) return;
        if (regions.length == 0) {
            System.out.println("No regions to delete");
            return;
        }
        
        var results = Arrays.stream(regions).map(Region::delete);
        System.out.println(results.count() + " regions deleted");
    }

    public static void delete() {
        var id = getNumber("Region id: ");
        var region = Region.get(id);

        if (region == null) {
            System.out.println("Region not found");
            return;
        }

        if (region.delete()) System.out.println("Region deleted");
    }

    public static void show() {
        var regions = Region.getAll();

        if (regions == null) return;
        if (regions.length == 0) {
            System.out.println("No regions to show");
            return;
        }

        Arrays.stream(regions).forEach(System.out::println);
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
            case 5 -> clear();
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) menu();
    }
}
