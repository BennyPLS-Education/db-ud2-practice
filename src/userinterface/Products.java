package userinterface;

import orm.Producer;
import orm.Product;
import orm.Region;

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
        
        if (new Product(0, name, producer, quality).insert()) System.out.println("Product added");
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
        Boolean result = null;
        while (true) {
            switch (getNumber("Select a field to update: ")) {
                case 1 -> {
                    System.out.print("Name: ");
                    final var name = input.nextLine();
                    result = new Product(id, name, product.producer(), product.quality()).update();
                }
                case 2 -> {
                    var producer = getNumber("Producer id: ");
                    result = new Product(id, product.name(), producer, product.quality()).update();
                }
                case 3 -> {
                    var quality = qualitySelector();
                    result = new Product(id, product.name(), product.producer(), quality).update();
                }
                default -> System.out.println("Option not valid");
            }
            if (result != null) {
                if (result) System.out.println("Product updated");
                break;
            }
        }
    }
    
    public static void clear() {
        var products = Product.getAll();
        
        if (products == null) return;
        if (products.length == 0) {
            System.out.println("No products to delete");
            return;
        }
        
        var results = Arrays.stream(products).map(Product::delete);
        
        if (results.allMatch(Boolean::booleanValue)) System.out.println("Products deleted");
    }

    public static void delete() {
        var id = getNumber("Product id: ");
        var product = Product.get(id);

        if (product == null) {
            System.out.println("Product not found");
            return;
        }
    
        if (product.delete()) System.out.println("Product deleted");
    }

    public static void show() {
        var products = Product.getAll();

        if (products == null) return;
        if (products.length == 0) {
            System.out.println("No products to show");
            return;
        }

        Arrays.stream(products).forEach(System.out::println);
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
            case 5 -> clear();
            default -> System.out.println("Option Not valid");
        }

        if (option != 0) menu();
    }
}
