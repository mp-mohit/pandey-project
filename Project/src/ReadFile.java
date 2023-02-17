import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class ReadFile {

    public List<Product> getProductList() throws IOException {
        File f = new File("C:/Mohit/Pandey/pandey-project/Product Files/");
        List<Product> products =new ArrayList<>();
        if (f.isDirectory()) {
            for (File val : f.listFiles()) {
                FileInputStream file = new FileInputStream(val);
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                System.out.println("File Name "+val.getName());
                XSSFSheet sheet = workbook.getSheet(val.getName().substring(0, val.getName().lastIndexOf(".")));
                // Get iterator to all the rows in current sheet
                Iterator<Row> rowIterator = sheet.iterator();
                int header = 0;
                // Traversing over each row of XLSX file
                while (rowIterator.hasNext()) {
                        Row row = rowIterator.next();
                        // For each row, iterate through each columns
                        Iterator<Cell> cellIterator = row.cellIterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
//                            System.out.println(cell.getStringCellValue());
                            StringTokenizer st = new StringTokenizer(cell.getStringCellValue(), "|");
                            if (header != 0) {
                                int k = 1;
                                Product product = new Product();
                                while (st.hasMoreElements()) {
//                            ID|NAME|COLOUR|GENDER_RECOMMENDATION|SIZE|PRICE|RATING|AVAILABILITY
                                    if (k == 1) {
                                        product.setProductId(st.nextToken());
                                        k++;
                                    } else if (k == 2) {
                                        product.setName(st.nextToken());
                                        k++;
                                    } else if (k == 3) {
                                        product.setColor(st.nextToken());
                                        k++;
                                    } else if (k == 4) {
                                        product.setGenderRecommendation(st.nextToken());
                                        k++;
                                    } else if (k == 5) {
                                        product.setSize(st.nextToken());
                                        k++;
                                    } else if (k == 6) {
                                        String price = st.nextToken();
                                        product.setPrice(Double.parseDouble(price));
                                        k++;
                                    } else if (k == 7) {
                                        String price = st.nextToken();
                                        product.setRating(Double.parseDouble(price));
                                        k++;
                                    } else if (k == 8) {
                                        if(String.valueOf(st.nextToken()).equals("Y")){
                                            product.setAvailability(true);
                                        }
                                        else {
                                            product.setAvailability(false);
                                        }
                                        k++;
                                    }
                                }
                                products.add(product);
                            }
                            header++;
                        }
                    }
                }
//            System.out.println(products);
        } else {
            System.out.println("File Not Found");
        }
        return products;
    }

}
