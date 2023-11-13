package userinterface;

import orm.Client;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class Clients {
    public static void add() {
        Scanner input = new Scanner(System.in);

        System.out.print("Name: ");
        var name = input.nextLine();

        var type = utils.Inputs.customerTypeSelector();

        new orm.Client(0, name, type).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var id = getNumber("Id: ");

        var client = orm.Client.get(id);

        if (client == null) {
            System.out.println("Client not found");
            return;
        }

        System.out.print("Name: ");
        var name = input.nextLine();

        var type = utils.Inputs.customerTypeSelector();

        new orm.Client(id, name, type).update();
    }

    public static void delete() {
        var id = getNumber("Id: ");

        var client = orm.Client.get(id);

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
