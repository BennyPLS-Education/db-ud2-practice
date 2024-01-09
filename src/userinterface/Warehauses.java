package userinterface;

import orm.Warehouse;
import utils.CSV;

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
    
        if (new Warehouse(0, name, region).insert()) System.out.println("Warehouse added");
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
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    final var name = input.nextLine();
                    result = new Warehouse(id, name, warehouse.region()).update();
                }
                case 2 -> {
                    var region = getNumber("Region id: ");
                    result = new Warehouse(id, warehouse.name(), region).update();
                }
                default -> System.out.println("Option not valid");
            }
            if (result != null) {
                if (result) System.out.println("Warehouse updated");
                break;
            }
        }
    }
    
    public static void clear() {
        var warehouses = Warehouse.getAll();
        
        if (warehouses == null) return;
        if (warehouses.length == 0) {
            System.out.println("No warehouses to delete");
            return;
        }
        
        var results = Arrays.stream(warehouses).map(Warehouse::delete);
        if (results.allMatch(Boolean::booleanValue)) System.out.println("Warehouses deleted");
    }
    
    public static void delete() {
        var id = getNumber("Warehouse id: ");
        var warehouse = Warehouse.get(id);
        
        if (warehouse == null) {
            System.out.println("Warehouse not found");
            return;
        }
        
        if (warehouse.delete()) System.out.println("Warehouse deleted");
    }
    
    public static void show() {
        var warehouses = Warehouse.getAll();
        
        if (warehouses == null) return;
        if (warehouses.length == 0) {
            System.out.println("No warehouses to show");
            return;
        }
        
        Arrays.stream(warehouses).forEach(System.out::println);
    }
    
    public static void toCSV() {
        var warehouses = Warehouse.getAll();
        
        if (warehouses == null) return;
        if (warehouses.length == 0) {
            System.out.println("No warehouses to export");
            return;
        }
        
        CSV.export(Arrays.asList(warehouses), "warehouses.csv");
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
            case 5 -> clear();
            case 6 -> toCSV();
            default -> System.out.println("Option Not valid");
        }
        
        if (option != 0) menu();
    }
}
