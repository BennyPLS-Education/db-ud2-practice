package userinterface;

import orm.Client;

import java.util.Arrays;

import static orm.CustomerType.customerTypeSelector;
import static utils.Inputs.*;

public class Clients {
    public static void add() {
        final var name = getString("Name: ");
        
        var type = customerTypeSelector();
        
        new Client(0, name, type).insert();
    }
    
    public static void update() {
        var id = getNumber("Id: ");
        
        var client = orm.Client.get(id);
        
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        
        var name = getString("Name: ");
        var type = customerTypeSelector();
        
        new orm.Client(id, name, type).update();
    }
    
    public static void delete() {
        var id = getNumber("Id: ");
        
        var client = Client.get(id);
        
        if (client == null) {
            System.out.println("Client not found");
            return;
        }
        
        client.delete();
    }
    
    public static void show() {
        Arrays.stream(Client.getAll()).forEach(System.out::println);
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
            default -> System.out.println("Option Not valid");
        }
        
        if (option != 0) menu();
    }
}
