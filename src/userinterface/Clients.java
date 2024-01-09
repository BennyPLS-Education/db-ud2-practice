package userinterface;

import orm.Client;
import utils.CSV;

import java.util.Arrays;

import static orm.CustomerType.customerTypeSelector;
import static utils.Inputs.*;

public class Clients {
    public static void add() {
        final var name = getString("Name: ");
        
        var type = customerTypeSelector();
        
        if (new Client(0, name, type).insert()) System.out.println("Client added");
    }
    
    public static void update() {
        var id = getNumber("Id: ");
        
        var client = orm.Client.get(id);
        
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        
        System.out.println("1. Name");
        System.out.println("2. Type");
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    final var name = getString("Name: ");
                    result = new Client(id, name, client.type()).update();
                }
                case 2 -> {
                    var type = customerTypeSelector();
                    result = new Client(id, client.name(), type).update();
                }
                default -> System.out.println("Option not valid");
            }
            if (result != null) {
                if (result) System.out.println("Client updated");
                break;
            }
        }
    }
    
    public static void clear() {
        var clients = Client.getAll();
        
        if (clients == null) return;
        if (clients.length == 0) {
            System.out.println("No clients to delete");
            return;
        }
        
        var results = Arrays.stream(clients).map(Client::delete);
        if (results.allMatch(Boolean::booleanValue)) System.out.println("Clients deleted");
    }
    
    public static void delete() {
        var id = getNumber("Id: ");
        
        var client = Client.get(id);
        
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        
        if (client.delete()) System.out.println("Client deleted");
    }
    
    public static void show() {
        var clients = Client.getAll();
        
        if (clients == null) return;
        if (clients.length == 0) {
            System.out.println("No clients to show");
            return;
        }
        
        Arrays.stream(clients).forEach(System.out::println);
    }
    
    public static void toCSV() {
        var clients = Client.getAll();
        
        if (clients == null) return;
        if (clients.length == 0) {
            System.out.println("No clients to show");
            return;
        }
        
        CSV.export(Arrays.asList(clients), "clients.csv");
    }
    
    public static void menu() {
        showMenu("Clients");
        
        var option = getNumber("Select an option:");
        
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
