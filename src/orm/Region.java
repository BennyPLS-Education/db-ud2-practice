package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Region(int id, String name, double x, double y) {
    
    public static Region createFrom(ResultSet set) throws SQLException {
        return new Region(
            set.getInt("id"),
            set.getString("name"),
            set.getDouble("x"),
            set.getDouble("y")
        );
    }
    
    public static Region[] getAll() {
        var regions = new ArrayList<Region>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM regions");
            while (set.next()) regions.add(Region.createFrom(set));
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return regions.toArray(new Region[0]);
    }
    
    public static Region get(int id) {
        Region region = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM regions WHERE id = " + id);
            if (set.next()) region = Region.createFrom(set);
        } catch (SQLException e) {manageError(e);}
        
        return region;
    }
    
    public boolean insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("INSERT INTO regions (name, x, y) VALUES (?, ?, ?)");
            
            statement.setString(1, name);
            statement.setDouble(2, x);
            statement.setDouble(3, y);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("UPDATE regions SET name = ?, x = ?, y = ? WHERE id = ?");
            
            statement.setString(1, name);
            statement.setDouble(2, x);
            statement.setDouble(3, y);
            statement.setInt(4, id);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("DELETE FROM regions WHERE id = ?");
            
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
        return String.format("Region: %d, %s, X: %s, Y: %s", id, name, x, y);
    }
}
