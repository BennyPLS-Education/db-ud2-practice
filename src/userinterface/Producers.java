package userinterface;

import orm.Producer;

import java.util.Arrays;
import java.util.Scanner;

import static utils.Inputs.*;

public class Producers {
    public static void add() {
        Scanner input = new Scanner(System.in);

        System.out.print("Name: ");
        var name = input.nextLine();

        var quality = qualitySelector();

        var region = getNumber("Region id: ");

        new orm.Producer(0, name, quality, region).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var id = getNumber("Id: ");

        var producer = orm.Producer.get(id);

        if (producer == null) {
            System.out.println("Producer not found");
            return;
        }

        System.out.print("Name: ");
        var name = input.nextLine();

        var quality = qualitySelector();

        var region = getNumber("Region id: ");

        new orm.Producer(id, name, quality, region).update();
    }

    public static void delete() {
        var id = getNumber("Id: ");

        var producer = orm.Producer.get(id);

        if (producer == null) {
            System.out.println("Producer not found");
            return;
        }

        producer.delete();
    }

    public static void show() {
        Arrays.stream(Producer.getAll()).forEach(System.out::println);
    }

    public static void menu() {
        showMenu("Producers");

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
