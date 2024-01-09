package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Warehouse(int id, String name, int region) {
    public static Warehouse createFrom(ResultSet set) throws SQLException {
        return new Warehouse(
            set.getInt("id"),
            set.getString("name"),
            set.getInt("region")
        );
    }
    
    public static Warehouse[] getAll() {
        ArrayList<Warehouse> warehouses = new ArrayList<>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM warehouses");
            while (set.next()) warehouses.add(Warehouse.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return warehouses.toArray(new Warehouse[0]);
    }
    
    public static Warehouse get(int id) {
        Warehouse warehouse = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM warehouses WHERE id = " + id);
            if (set.next()) warehouse = Warehouse.createFrom(set);
        } catch (SQLException e) {manageError(e);}
        
        return warehouse;
    }
    
    public boolean insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("INSERT INTO warehouses (name, region) VALUES (?, ?)");
            
            statement.setString(1, name);
            statement.setInt(2, region);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("UPDATE warehouses SET name = ?, region = ? WHERE id = ?");
            
            statement.setString(1, name);
            statement.setInt(2, region);
            statement.setInt(3, id);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("DELETE FROM warehouses WHERE id = ?");
            
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
        return String.format("Warehouse: %d, %s, %s", id, name, Region.get(region).name());
    }
}
