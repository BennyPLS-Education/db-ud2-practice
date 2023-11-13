package orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.Exceptions.manageError;

public record Product(
    int id,
    String name,
    int producer,
    Quality quality
) {
    public static Product createFrom(ResultSet set) throws SQLException {
        return new Product(
            set.getInt("id"),
            set.getString("name"),
            set.getInt("producer"),
            Quality.valueOf(set.getString("quality"))
        );
    }

    public static Product[] getAll() {
        var products = new ArrayList<Product>();

        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM products");
            while (set.next()) {
                products.add(Product.createFrom(set));
            }
        } catch (SQLException e) {manageError(e);}

        return products.toArray(new Product[0]);
    }

    public static Product get(int id) {
        Product product = null;

        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var set = connection.createStatement().executeQuery("SELECT * FROM products WHERE id = " + id);
            if (set.next()) {
                product = Product.createFrom(set);
            }
        } catch (SQLException e) {manageError(e);}

        return product;
    }

    public void insert() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("INSERT INTO products (name, producer, quality) VALUES (?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, producer);
            statement.setString(3, quality.name());
            statement.executeUpdate();
        } catch (SQLException e) {manageError(e);}
    }

    public void update() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("UPDATE products SET name = ?, producer = ?, quality = ? WHERE id = ?");
            statement.setInt(4, id);
            statement.setString(1, name);
            statement.setInt(2, producer);
            statement.setString(3, quality.name());
            statement.executeUpdate();
        } catch (SQLException e) {manageError(e);}
    }

    public void delete() {
        try (var connection = DriverManager.getConnection(DBData.URL, DBData.USER, DBData.PASSWORD)) {
            var statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {manageError(e);}
    }

    @Override
    public String toString() {
        return String.format(
            "Product id: %d, name: %s, producer: %s, quality: %s",
            id,
            name,
            Producer.get(producer).name(),
            quality
        );
    }
}
