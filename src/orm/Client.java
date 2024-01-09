package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Client(int id, String name, CustomerType type) {
    
    public static Client createFrom(ResultSet set) throws SQLException {
        return new Client(
            set.getInt("id"),
            set.getString("name"),
            CustomerType.parseString(set.getString("type"))
        );
    }
    
    public static Client[] getAll() {
        var clients = new ArrayList<Client>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            try (var statement = connection.createStatement()) {
                try (var set = statement.executeQuery("SELECT * FROM clients")) {
                    while (set.next()) clients.add(Client.createFrom(set));
                }
            }
        } catch (SQLException e) {
            manageError(e);
            return null;
        }
        
        return clients.toArray(new Client[0]);
    }
    
    public static Client get(int id) {
        Client client = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("SELECT * FROM clients WHERE id = ?");
            
            statement.setInt(1, id);
            
            var set = statement.executeQuery();
            if (set.next()) client = Client.createFrom(set);
        } catch (SQLException e) {manageError(e);}
        
        return client;
    }
    
    public boolean insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("INSERT INTO clients (name, type) VALUES (?, ?)");
            
            statement.setString(1, name);
            statement.setString(2, type.name());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            manageError(e);
            return false;
        }
        
        return true;
    }
    
    public boolean update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("UPDATE clients SET name = ?, type = ? WHERE id = ?");
            
            statement.setString(1, name);
            statement.setString(2, type.name());
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
            var statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");
            
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
            "Client: %d, %s, %s",
            id,
            name,
            type
        );
    }
}
