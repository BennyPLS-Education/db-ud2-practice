import orm.DBData;
import orm.Region;
import orm.Warehouse;
import userinterface.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static orm.CustomerType.customerTypeSelector;
import static utils.Exceptions.manageError;
import static utils.Inputs.getNumber;
import static utils.Inputs.getString;

public class Main {
    public static void main(String[] args) {
        int option;
        
        System.out.println("-------------------------");
        System.out.println("    SELECT AN ACTION:    ");
        System.out.println("-------------------------");
        System.out.println("0. Exit");
        System.out.println("1. Menu Products");
        System.out.println("2. Menu Producers");
        System.out.println("3. Menu Regions");
        System.out.println("4. Menu Warehouses");
        System.out.println("5. Menu Clients");
        System.out.println("6. Menu Orders");
        System.out.println("7. Menu Warehouse Products");
        System.out.println("8. Order Product/s");
        System.out.println("9. Clear Data");
        System.out.println("10. Export all to CSV");
        System.out.println("-------------------------");
        
        option = getNumber("Option: ");
        
        switch (option) {
            case 0 -> System.out.print("");
            
            case 1 -> Products.menu();
            case 2 -> Producers.menu();
            case 3 -> Regions.menu();
            case 4 -> Warehauses.menu();
            case 5 -> Clients.menu();
            case 6 -> Orders.menu();
            case 7 -> WarehousesProducts.menu();
            case 8 -> orderProducts();
            case 9 -> clearData();
            case 10 -> exportAll();
            default -> System.out.println("Option Not valid");
        }
        
        if (option != 0) main(args);
    }
    
    public static void clearData() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.createStatement();
            
            statement.addBatch("DELETE FROM warehouses_products");
            statement.addBatch("DELETE FROM orders");
            statement.addBatch("DELETE FROM clients");
            statement.addBatch("DELETE FROM warehouses");
            statement.addBatch("DELETE FROM products");
            statement.addBatch("DELETE FROM producers");
            statement.addBatch("DELETE FROM regions");
            
            statement.executeBatch();
        } catch (SQLException e) {manageError(e);}
    }
    
    public static void orderProducts() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            var statement = connection.createStatement();
            
            // ask if register a new client or login?
            var option = getNumber("1. Register a new client\n2. Login\nOption: ");
            
            while (option != 1 && option != 2) {
                System.out.println("Option not valid");
                option = getNumber("1. Register a new client\n2. Login\nOption: ");
            }
            
            var clientId = 0;
            
            if (option == 1) {
                var name = getString("Name: ");
                var type = customerTypeSelector();
                
                var rs = statement.executeQuery("SELECT * FROM clients ORDER BY id DESC LIMIT 1");
                
                if (rs.next()) clientId = rs.getInt("id") + 1;
                
                statement.executeUpdate("INSERT INTO clients (id, name, type) VALUES (" + clientId + ", '" + name + "', '" + type + "')");
            } else {
                var rs = statement.executeQuery("SELECT * FROM clients");
                var clientsIDs = new ArrayList<Integer>();
                System.out.println("CLIENTS");
                
                while (rs.next()) {
                    clientsIDs.add(rs.getInt("id"));
                    System.out.printf("ID: %d. %s, Type: %s %n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type")
                    );
                }
                
                clientId = getNumber("Client id: ");
                
                while (!clientsIDs.contains(clientId)) {
                    System.out.println("Client not found");
                    clientId = getNumber("Client id: ");
                }
            }
            
            var rs = statement.executeQuery("SELECT * FROM products");
            var productsIDs = new ArrayList<Integer>();
            System.out.println("PRODUCTS");
            
            while (rs.next()) {
                productsIDs.add(rs.getInt("id"));
                System.out.printf("ID: %d. %s, Quality: %s %n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("quality")
                );
            }
            
            var productId = getNumber("Product id: ");
            
            while (!productsIDs.contains(productId)) {
                System.out.println("Product not found");
                productId = getNumber("Product id: ");
            }
            
            rs = statement.executeQuery("SELECT w.id, w.name, r.name as 'region', quantity FROM warehouses_products " +
                                        "JOIN inventory.warehouses w ON w.id = warehouses_products.warehouse " +
                                        "JOIN inventory.regions r ON r.id = w.region " +
                                        "WHERE product = " + productId);
            
            var warehousesIDs = new ArrayList<Integer>();
            System.out.println("WAREHOUSES");
            
            while (rs.next()) {
                warehousesIDs.add(rs.getInt("id"));
                System.out.printf("ID: %d. %s, Region: %s, Quantity: %d %n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("region"),
                    rs.getInt("quantity")
                );
            }
            
            int warehaouseId = getNumber("Warehouse id: ");
            
            while (!warehousesIDs.contains(warehaouseId)) {
                System.out.println("Warehouse not found");
                warehaouseId = getNumber("Warehouse id: ");
            }
            
            var quantity = getNumber("Quantity: ");
            
            while (quantity < 1) {
                System.out.println("Quantity must be greater than 0");
                quantity = getNumber("Quantity: ");
            }
            
            statement.executeUpdate("INSERT INTO orders (client, product, warehouse, quantity) VALUES (" + clientId + ", " + productId + ", " + warehaouseId + ", " + quantity + ")");
            
            System.out.println("Order created successfully");
            
            connection.commit();
        } catch (SQLException e) {manageError(e);}
    }
    
    public static void exportAll() {
        Regions.toCSV();
        Producers.toCSV();
        Products.toCSV();
        Warehauses.toCSV();
        Clients.toCSV();
        Orders.toCSV();
        WarehousesProducts.toCSV();
    }
}
