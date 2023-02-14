import java.sql.*;
import java.util.Scanner;

public class ProductSearch {

public static void main(String[] args) {
    // read input parameters
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter color of T-shirt: ");
    String color = scanner.nextLine();
    System.out.println("Enter size of T-shirt: ");
    String size = scanner.nextLine();
    System.out.println("Enter gender for T-shirt (M/F): ");
    String gender = scanner.nextLine();
    System.out.println("Enter output preference (Price/Rating/Both): ");
    String outputPref = scanner.nextLine();

    // connect to the database
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "username", "password")) {

        // check if tables exist, if not create them
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE 'nike_tshirts'");
        if (!rs.next()) {
            stmt.execute("CREATE TABLE nike_tshirts (id INT PRIMARY KEY, name VARCHAR(50), color VARCHAR(20), gender VARCHAR(1), size VARCHAR(3), price FLOAT, rating INT, availability VARCHAR(1))");
        }
        // similar create table statements for other companies

        // load csv data into database
        CSVParser.loadCSVData(conn, "nike_tshirts.csv", "nike_tshirts");
        // similar load statements for other companies

        // search for matching T-shirts and sort by output preference
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM nike_tshirts UNION ALL SELECT * FROM puma_tshirts UNION ALL SELECT * FROM adidas_tshirts WHERE color = ? AND size = ? AND gender = ? ORDER BY " + outputPref);
        ps.setString(1, color);
        ps.setString(2, size);
        ps.setString(3, gender);
        rs = ps.executeQuery();

        // print results
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ", " + rs.getString("name") + ", " + rs.getString("color") + ", " + rs.getString("gender") + ", " + rs.getString("size") + ", " + rs.getFloat("price") + ", " + rs.getInt("rating") + ", " + rs.getString("availability"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}