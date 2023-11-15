package orm;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Order(
    int id,
    int warehouse,
    int product,
    int client,
    int quantity,
    Date delivered,
    Date ordered,
    Status status
) {
    
    public Order(int id, int warehouse, int product, int client, int quantity) {
        this(id, warehouse, product, client, quantity, null, null, null);
    }
    
    public static Order createFrom(ResultSet set) throws SQLException {
        return new Order(
            set.getInt("id"),
            set.getInt("warehouse"),
            set.getInt("product"),
            set.getInt("client"),
            set.getInt("quantity"),
            set.getDate("date_delivered"),
            set.getDate("date_ordered"),
            Status.parseString(set.getString("status"))
        );
    }
    
    public static Order[] getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM orders");
            while (set.next()) orders.add(Order.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return orders.toArray(new Order[0]);
    }
    
    public static Order get(int id) {
        Order order = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM orders WHERE id = " + id);
            if (set.next()) order = Order.createFrom(set);
        } catch (SQLException e) {manageError(e);}
        
        return order;
    }
    
    public boolean insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement(
                "INSERT INTO orders (warehouse, product, client, quantity) VALUES (?, ?, ?, ?)");
            
            statement.setInt(1, warehouse);
            statement.setInt(2, product);
            statement.setInt(3, client);
            statement.setInt(4, quantity);
            
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
                "UPDATE orders SET warehouse = ?, product = ?, client = ?, quantity = ?, date_delivered = ?, date_ordered = ?, status = ? WHERE id = ?"
            );
            statement.setInt(8, id);
            
            statement.setInt(1, warehouse);
            statement.setInt(2, product);
            statement.setInt(3, client);
            statement.setInt(4, quantity);
            statement.setDate(5, delivered);
            statement.setDate(6, ordered);
            statement.setString(7, status.toString());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Order: %d, %s, %s, %s, %d, %s, %s, %s",
            id,
            Warehouse.get(warehouse).name(),
            Product.get(product).name(),
            Client.get(client).name(),
            quantity,
            delivered,
            ordered,
            status
        );
    }
}
