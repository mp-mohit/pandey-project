import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {


private static final String CSV_SEPARATOR = ",";

// Method to read and parse CSV file
public static List<Product> parseCSV(File file) {
    List<Product> products = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(CSV_SEPARATOR);
            Product product = new Product();
            product.setId(data[0);
            product.setName(data[1]);
            product.setColor(data[2]);
            product.setGender(data[3]);
            product.setSize(data[4]);
            product.setPrice(Double.parseDouble(data[5]));
            product.setRating(Integer.parseInt(data[6]));
            product.setAvailability(data[7].equals("Y"));
            products.add(product);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return products;
}
}