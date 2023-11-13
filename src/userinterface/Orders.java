package userinterface;

import orm.Order;
import orm.Status;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.*;

public class Orders {
    public static void add() {
        Scanner input = new Scanner(System.in);

        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        var client = getNumber("Client id: ");
        var quantity = getNumber("Quantity: ");

        new orm.Order(0, warehouse, product, client, quantity).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var id = getNumber("Order id: ");

        var order = orm.Order.get(id);

        if (order == null) {
            System.out.println("Order not found");
            return;
        }

        System.out.println("1. Warehouse");
        System.out.println("2. Product");
        System.out.println("3. Client");
        System.out.println("4. Quantity");
        System.out.println("5. Status");
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    var warehouse = getNumber("Warehouse id: ");
                    new orm.Order(id, warehouse, order.product(), order.client(), order.quantity()).update();
                    return;
                }
                case 2 -> {
                    var product = getNumber("Product id: ");
                    new orm.Order(id, order.warehouse(), product, order.client(), order.quantity()).update();
                    return;
                }
                case 3 -> {
                    var client = getNumber("Client id: ");
                    new orm.Order(id, order.warehouse(), order.product(), client, order.quantity()).update();
                    return;
                }
                case 4 -> {
                    var quantity = getNumber("Quantity: ");
                    new orm.Order(id, order.warehouse(), order.product(), order.client(), quantity).update();
                    return;
                }
                case 5 -> {
                    Status status = statusSelector();
                    new orm.Order(id, order.warehouse(), order.product(), order.client(), order.quantity(), order.delivered(), order.ordered(), status).update();
                }
                default -> System.out.println("Option Not valid");
            }
        }
    }

    public static void delete() {
        var id = getNumber("Order id: ");

        var order = orm.Order.get(id);

        if (order == null) {
            System.out.println("Order not found");
            return;
        }

        order.delete();
    }

    public static void show() {
        Arrays.stream(Order.getAll()).forEach(System.out::println);
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
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) menu();
    }
}
