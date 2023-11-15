package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record WarehouseProduct(int warehouseId, int productId, int quantity) {
    public static WarehouseProduct createFrom(ResultSet set) throws SQLException {
        return new WarehouseProduct(
            set.getInt("warehouse"),
            set.getInt("product"),
            set.getInt("quantity")
        );
    }
    
    public static WarehouseProduct[] getAll() {
        ArrayList<WarehouseProduct> warehousesProducts = new ArrayList<>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM warehouses_products");
            while (set.next()) warehousesProducts.add(WarehouseProduct.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return warehousesProducts.toArray(new WarehouseProduct[0]);
    }
    
    public static WarehouseProduct[] getAllFromWarehouse(int warehouseId) {
        ArrayList<WarehouseProduct> warehousesProducts = new ArrayList<>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement()
                .executeQuery("SELECT * FROM warehouses_products WHERE warehouse = " + warehouseId);
            while (set.next()) warehousesProducts.add(WarehouseProduct.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return warehousesProducts.toArray(new WarehouseProduct[0]);
    }
    
    public static WarehouseProduct[] getAllFromProduct(int productId) {
        ArrayList<WarehouseProduct> warehousesProducts = new ArrayList<>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement()
                .executeQuery("SELECT * FROM warehouses_products WHERE product = " + productId);
            while (set.next()) warehousesProducts.add(WarehouseProduct.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return warehousesProducts.toArray(new WarehouseProduct[0]);
    }
    
    public static WarehouseProduct get(int warehouseId, int productId) {
        WarehouseProduct warehouseProduct = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement()
                .executeQuery("SELECT * FROM warehouses_products WHERE warehouse = " + warehouseId + " AND product = " + productId);
            if (set.next()) warehouseProduct = WarehouseProduct.createFrom(set);
        } catch (SQLException e) {manageError(e);}
        
        return warehouseProduct;
    }
    
    public boolean insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement(
                "INSERT INTO warehouses_products (warehouse, product, quantity) VALUES (?, ?, ?)");
            
            statement.setInt(1, warehouseId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement(
                "UPDATE warehouses_products SET quantity = ? WHERE warehouse = ? AND product = ?");
            
            statement.setInt(1, quantity);
            statement.setInt(2, warehouseId);
            statement.setInt(3, productId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement(
                "DELETE FROM warehouses_products WHERE warehouse = ? AND product = ?");
            
            statement.setInt(1, warehouseId);
            statement.setInt(2, productId);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("Warehouse Product: %s, %s, %s",
            Warehouse.get(warehouseId).name(),
            Product.get(productId).name(),
            quantity);
    }
}
