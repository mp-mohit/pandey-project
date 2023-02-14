import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/productdb";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (id, name, color, gender_recommendation, size, price, rating, availability) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getColor());
            statement.setString(4, product.getGenderRecommendation());
            statement.setString(5, product.getSize());
            statement.setDouble(6, product.getPrice());
            statement.setDouble(7, product.getRating());
            statement.setBoolean(8, product.isAvailability());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET name=?, color=?, gender_recommendation=?, size=?, price=?, rating=?, availability=? WHERE id=?");
            statement.setString(1, product.getName());
            statement.setString(2, product.getColor());
            statement.setString(3, product.getGenderRecommendation());
            statement.setString(4, product.getSize());
            statement.setDouble(5, product.getPrice());
            statement.setDouble(6, product.getRating());
            statement.setBoolean(7, product.isAvailability());
            statement.setLong(8, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> searchProducts(String color, String size, String gender, String outputPreference) {
        List<Product> productList = new ArrayList<>();
        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM products WHERE availability = true");
            if (color != null && !color.isEmpty()) {
                queryBuilder.append(" AND color = '").append(color).append("'");
            }
            if (size != null && !size.isEmpty()) {
                queryBuilder.append(" AND size = '").append(size).append("'");
            }
            if (gender != null && !gender.isEmpty()) {
                queryBuilder.append(" AND gender_recommendation = '").append(gender).append("'");
            }
            if (outputPreference != null && !outputPreference.isEmpty()) {
                if (outputPreference.equalsIgnoreCase("price")) {
                    queryBuilder.append(" ORDER BY price ASC");
                } else if (outputPreference.equalsIgnoreCase("rating")) {
                    queryBuilder.append(" ORDER BY rating DESC");
                } else if (outputPreference.equalsIgnoreCase("price_rating")) {
                    queryBuilder.append(" ORDER BY price ASC, rating DESC");
                }
            }
            PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setColor(resultSet.getString("color"));
                product.setGenderRecommendation(resultSet.getString("gender_recommendation"));
                product.setSize(resultSet.getString("size"));
                product.setPrice(resultSet.getDouble("price"));
                product.setRating((int) resultSet.getDouble("rating"));
                product.setAvailability(resultSet.getBoolean("availability"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}