package userinterface;

import orm.Order;
import orm.Producer;
import orm.WarehouseProduct;

import java.util.Arrays;
import java.util.Scanner;

import static orm.Quality.qualitySelector;
import static utils.Inputs.*;

public class Producers {
    public static void add() {
        Scanner input = new Scanner(System.in);

        System.out.print("Name: ");
        var name = input.nextLine();

        var quality = qualitySelector();

        var region = getNumber("Region id: ");

        if (new orm.Producer(0, name, quality, region).insert()) System.out.println("Producer added");
    }

    public static void update() {
        Scanner input = new Scanner(System.in);

        var id = getNumber("Id: ");

        var producer = orm.Producer.get(id);

        if (producer == null) {
            System.out.println("Producer not found");
            return;
        }
        
        System.out.println("1. Name");
        System.out.println("2. Quality");
        System.out.println("3. Region");
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    final var name = input.nextLine();
                    result = new orm.Producer(id, name, producer.quality(), producer.region()).update();
                }
                case 2 -> {
                    var quality = qualitySelector();
                    result = new orm.Producer(id, producer.name(), quality, producer.region()).update();
                }
                case 3 -> {
                    var region = getNumber("Region id: ");
                    result = new orm.Producer(id, producer.name(), producer.quality(), region).update();
                }
                default -> System.out.println("Option not valid");
            }
            if (result != null) {
                if (result) System.out.println("Producer updated");
                break;
            }
        }
    }

    public static void delete() {
        var id = getNumber("Id: ");

        var producer = orm.Producer.get(id);

        if (producer == null) {
            System.out.println("Producer not found");
            return;
        }

        if (producer.delete()) System.out.println("Producer deleted");
    }
    
    public static void clear() {
        var producers = Producer.getAll();
        
        if (producers == null) return;
        if (producers.length == 0) {
            System.out.println("No producers to delete");
            return;
        }
        
        var results = Arrays.stream(producers).map(Producer::delete);
        if (results.allMatch(Boolean::booleanValue)) System.out.println("Producers deleted");
    }

    public static void show() {
        var producers = Producer.getAll();

        if (producers == null) return;
        if (producers.length == 0) {
            System.out.println("No producers to show");
            return;
        }

        Arrays.stream(producers).forEach(System.out::println);
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
            case 5 -> clear();
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) menu();
    }
}
