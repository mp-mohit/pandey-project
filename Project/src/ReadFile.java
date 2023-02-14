import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {

    public static void main(String[] args) throws IOException {
        File f = new File("C:/Mohit/Pandey/pandey-project/Product Files/");
        if(f.isDirectory()){
            for (File val:f.listFiles()){
                FileInputStream file = new FileInputStream(val);
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                System.out.println("in workbook");
                XSSFSheet sheet = workbook.getSheet("Shipments");
                System.out.println("got sheet");
                System.out.println("File is their");
            }
        }
        else {
            System.out.println("File Not Found");
        }
    }

}
