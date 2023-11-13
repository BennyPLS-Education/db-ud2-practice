package userinterface;

import orm.Product;

import java.util.Arrays;
import java.util.Scanner;

import static orm.Quality.qualitySelector;
import static utils.Inputs.*;

public class Products {
    public static void add() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        var name = input.nextLine();
        var producer = getNumber("Producer id: ");
        var quality = qualitySelector();

        new Product(0, name, producer, quality).insert();
    }

    public static void update() {
        Scanner input = new Scanner(System.in);
        var id = getNumber("Product id: ");

        var product = Product.get(id);

        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        System.out.println("1. Name");
        System.out.println("2. Producer");
        System.out.println("3. Quality");
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    var name = input.nextLine();
                    new Product(id, name, product.producer(), product.quality()).update();
                    return;
                }
                case 2 -> {
                    var producer = getNumber("Producer id: ");
                    new Product(id, product.name(), producer, product.quality()).update();
                    return;
                }
                case 3 -> {
                    var quality = qualitySelector();
                    new Product(id, product.name(), product.producer(), quality).update();
                    return;
                }
                default -> System.out.println("Option Not valid");
            }
        }
    }

    public static void delete() {
        var id = getNumber("Product id: ");
        var product = Product.get(id);

        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        product.delete();
    }

    public static void show() {
        Arrays.stream(Product.getAll()).forEach(System.out::println);
    }

    public static void menu() {
        showMenu("Products");

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
