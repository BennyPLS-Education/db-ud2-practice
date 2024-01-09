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
    
    public static void showByProduct() {
        var product = getNumber("Product id: ");
        
        var warehouseProducts = WarehouseProduct.getAllFromProduct(product);
        
        if (warehouseProducts == null) return;
        if (warehouseProducts.length == 0) {
            System.out.println("No warehouse products to show");
            return;
        }
        
        Arrays.stream(warehouseProducts).forEach(System.out::println);
    }
    
    public static void showByWarehouse() {
        var warehouse = getNumber("Warehouse id: ");
        
        var warehouseProducts = WarehouseProduct.getAllFromWarehouse(warehouse);
        
        if (warehouseProducts == null) return;
        if (warehouseProducts.length == 0) {
            System.out.println("No warehouse products to show");
            return;
        }
        
        Arrays.stream(warehouseProducts).forEach(System.out::println);
    }
    
    public static void toCSV() {
        var warehouseProducts = WarehouseProduct.getAll();
        
        if (warehouseProducts == null) return;
        if (warehouseProducts.length == 0) {
            System.out.println("No warehouse products to export");
            return;
        }
        
        utils.CSV.export(Arrays.asList(warehouseProducts), "warehouse_products.csv");
    }
    
    public static void menu() {
        showMenu("Warehouse Product");
        System.out.println("/t Unique");
        System.out.println("-------------------------");
        System.out.println("7. Show by warehouse");
        System.out.println("8. Show by product");
        System.out.println("-------------------------");
        
        var option = getNumber("Select an option: ");
        
        switch (option) {
            case 0 -> System.out.print("");
            case 1 -> show();
            case 2 -> add();
            case 3 -> update();
            case 4 -> delete();
            case 5 -> clear();
            case 6 -> toCSV();
            case 7 -> showByWarehouse();
            case 8 -> showByProduct();
            default -> System.out.println("Option Not valid");
        }
        
        if (option != 0) menu();
    }
}
