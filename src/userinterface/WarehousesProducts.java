package userinterface;

import orm.WarehouseProduct;

import java.util.Arrays;

import static utils.Inputs.getNumber;
import static utils.Inputs.showMenu;

public class WarehousesProducts {
    public static void add() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        var quantity = getNumber("Quantity: ");
        
        if (new WarehouseProduct(warehouse, product, quantity).insert()) System.out.println("Warehouse product added");
    }
    
    public static void update() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        
        var warehouseProduct = WarehouseProduct.get(warehouse, product);
        
        if (warehouseProduct == null) {
            System.out.println("Warehouse product not found");
            return;
        }
        
        var quantity = getNumber("Quantity: ");
        
        if (new WarehouseProduct(warehouse, product, quantity).update()) System.out.println("Warehouse product updated");
    }
    
    public static void delete() {
        var warehouse = getNumber("Warehouse id: ");
        var product = getNumber("Product id: ");
        
        var warehouseProduct = WarehouseProduct.get(warehouse, product);
        
        if (warehouseProduct == null) {
            System.out.println("Warehouse product not found");
            return;
        }
        
        if (warehouseProduct.delete()) System.out.println("Warehouse product deleted");
    }
    
    public static void clear() {
        var warehouseProducts = WarehouseProduct.getAll();
        
        if (warehouseProducts == null) return;
        if (warehouseProducts.length == 0) {
            System.out.println("No warehouse products to delete");
            return;
        }
        
        var result = Arrays.stream(warehouseProducts).map(WarehouseProduct::delete);
        if (result.allMatch(Boolean::booleanValue)) System.out.println("Warehouse products deleted");
    }
    
    public static void show() {
        var warehouseProducts = WarehouseProduct.getAll();
        
        if (warehouseProducts == null) return;
        if (warehouseProducts.length == 0) {
            System.out.println("No warehouse products to show");
            return;
        }
        
        Arrays.stream(warehouseProducts).forEach(System.out::println);
    }
    
    public static void menu() {
        showMenu("Warehouse Product");
        
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
