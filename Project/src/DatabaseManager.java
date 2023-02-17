import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "project";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection Established");
            ReadFile readFile = new ReadFile();
            List<Product> products = readFile.getProductList();
            for (Product product : products) {
                Boolean ifExist = ifProductExist(product);
                if (ifExist) {
                    updateProduct(product);
                } else {
                    insertProduct(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
    }

    public void insertProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.product(\n" +
                    "             availability, color, gender_recommendation, name, price, product_id, rating, size)\n" +
                    "    VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(4, product.getName());
            statement.setString(2, product.getColor());
            statement.setString(3, product.getGenderRecommendation());
            statement.setString(8, product.getSize());
            statement.setString(6, product.getProductId());
            statement.setDouble(5, product.getPrice());
            statement.setDouble(7, product.getRating());
            statement.setBoolean(1, product.isAvailability());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean ifProductExist(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from public.product where product_id = ?;");
            statement.setString(1, product.getProductId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sortByProductId() {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from public.product order by product_id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        (resultSet.getString("product_id") + ", " + resultSet.getString("name") + ", " +
                                resultSet.getString("color") + ", " +
                                resultSet.getString("gender_recommendation") + ", " +
                                resultSet.getString("size") + ", " +
                                resultSet.getDouble("price") + ", " +
                                resultSet.getDouble("rating") + ", " +
                                resultSet.getBoolean("availability")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void search(String color, String size, String gender, int type) {
        try {
            PreparedStatement statement = null;
            if (type == 1) {
                statement = connection.prepareStatement("select * from public.product WHERE color = ? AND size = ? AND gender = ? ORDER BY price;");
            } else if (type == 2) {
                statement = connection.prepareStatement("select * from public.product WHERE color = ? AND size = ? AND gender = ? ORDER BY rating;");
            } else if (type == 3) {
                statement = connection.prepareStatement("select * from public.product WHERE color = ? AND size = ? AND gender = ? ORDER BY price, rating;");
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        (resultSet.getString("product_id") + ", " + resultSet.getString("name") + ", " +
                                resultSet.getString("color") + ", " +
                                resultSet.getString("gender_recommendation") + ", " +
                                resultSet.getString("size") + ", " +
                                resultSet.getDouble("price") + ", " +
                                resultSet.getDouble("rating") + ", " +
                                resultSet.getBoolean("availability")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sortByName() {
        try {
            PreparedStatement statement = connection.prepareStatement("select * from public.product order by name");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        (resultSet.getString("product_id") + ", " + resultSet.getString("name") + ", " +
                                resultSet.getString("color") + ", " +
                                resultSet.getString("gender_recommendation") + ", " +
                                resultSet.getString("size") + ", " +
                                resultSet.getDouble("price") + ", " +
                                resultSet.getDouble("rating") + ", " +
                                resultSet.getBoolean("availability")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.product\n" +
                    "SET availability=?, color=?, gender_recommendation=?, name=?, price=?, rating=?, size=? WHERE product_id=?");
            statement.setBoolean(1, product.isAvailability());
            statement.setString(2, product.getColor());
            statement.setString(3, product.getGenderRecommendation());
            statement.setString(4, product.getName());
            statement.setDouble(5, product.getPrice());
            statement.setDouble(6, product.getRating());
            statement.setString(7, product.getSize());
            statement.setString(8, product.getProductId());
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