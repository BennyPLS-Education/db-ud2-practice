package userinterface;

import orm.Order;
import orm.Status;

import java.util.Arrays;

import static orm.Status.statusSelector;
import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class Orders {
    public static void add() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        var client = getNumber("Client id: ");
        var quantity = getNumber("Quantity: ");
        
        if (new Order(0, warehouse, product, client, quantity).insert()) System.out.println("Order added");
    }
    
    public static void update() {
        var id = getNumber("Order id: ");
        
        var order = Order.get(id);
        
        if (order == null) {
            System.out.println("Order not found");
            return;
        }
        
        System.out.println("1. Warehouse");
        System.out.println("2. Product");
        System.out.println("3. Client");
        System.out.println("4. Quantity");
        System.out.println("5. Status");
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    var warehouse = getNumber("Warehouse id: ");
                    result = new Order(id, warehouse, order.product(), order.client(), order.quantity()).update();
                }
                case 2 -> {
                    var product = getNumber("Product id: ");
                    result = new Order(id, order.warehouse(), product, order.client(), order.quantity()).update();
                }
                case 3 -> {
                    var client = getNumber("Client id: ");
                    result = new Order(id, order.warehouse(), order.product(), client, order.quantity()).update();
                }
                case 4 -> {
                    var quantity = getNumber("Quantity: ");
                    result = new Order(id, order.warehouse(), order.product(), order.client(), quantity).update();
                }
                case 5 -> {
                    Status status = statusSelector();
                    result = new Order(id,
                        order.warehouse(),
                        order.product(),
                        order.client(),
                        order.quantity(),
                        order.delivered(),
                        order.ordered(),
                        status).update();
                }
                default -> System.out.println("Option Not valid");
            }
            
            if (result != null) {
                if (result) System.out.println("Order updated");
                break;
            }
        }
    }
    
    public static void clear() {
        var clients = Order.getAll();
        
        if (clients == null) return;
        if (clients.length == 0) {
            System.out.println("No clients to delete");
            return;
        }
        
        var results = Arrays.stream(clients).map(Order::delete);
        if (results.allMatch(Boolean::booleanValue)) System.out.println("Orders deleted");
    }
    
    public static void delete() {
        var id = getNumber("Order id: ");
        
        var order = Order.get(id);
        
        if (order == null) {
            System.out.println("Order not found");
            return;
        }
        
        if (order.delete()) System.out.println("Order deleted");
    }
    
    public static void show() {
        var orders = Order.getAll();
        
        if (orders == null) return;
        if (orders.length == 0) {
            System.out.println("No orders to show");
            return;
        }
        
        Arrays.stream(orders).forEach(System.out::println);
    }
    
    public static void menu() {
        showMenu("Orders");
        
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
