package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Producer(int id, String name, Quality quality, int region) {
    
    public static Producer createFrom(ResultSet set) throws SQLException {
        return new Producer(
            set.getInt("id"),
            set.getString("name"),
            Quality.valueOf(set.getString("quality")),
            set.getInt("region")
        );
    }
    
    public static Producer[] getAll() {
        var producers = new ArrayList<Producer>();
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM producers");
            while (set.next()) producers.add(Producer.createFrom(set));
            
            System.out.println("Producers loaded");
        } catch (SQLException e) {manageError(e);}
        
        return producers.toArray(new Producer[0]);
    }
    
    public static Producer get(int id) {
        Producer producer = null;
        
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM producers WHERE id = " + id);
            if (set.next()) producer = Producer.createFrom(set);
            
            System.out.println("Producer loaded");
        } catch (SQLException e) {manageError(e);}
        
        return producer;
    }
    
    public void insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("INSERT INTO producers (name, quality, region) VALUES (?, ?, ?)");
            
            statement.setString(1, name);
            statement.setString(2, quality.name());
            statement.setInt(3, region);
            
            statement.executeUpdate();
            
            System.out.println("Producer inserted");
        } catch (SQLException e) {manageError(e);}
    }
    
    public void update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("UPDATE producers SET name = ?, quality = ?, region = ? WHERE id = ?");
            
            statement.setString(1, name);
            statement.setString(2, quality.name());
            statement.setInt(3, region);
            statement.setInt(4, id);
            
            statement.executeUpdate();
            
            System.out.println("Producer updated");
        } catch (SQLException e) {manageError(e);}
    }
    
    public void delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("DELETE FROM producers WHERE id = ?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            System.out.println("Producer deleted");
        } catch (SQLException e) {manageError(e);}
    }
    
    @Override
    public String toString() {
        return String.format("Producer: %s, Quality: %s, Region: %s", name, quality, Region.get(region).name());
    }
}
