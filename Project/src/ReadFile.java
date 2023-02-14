import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReadFile {

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("../../Product Files");
        for (File val:f.listFiles()){
            FileInputStream file = new FileInputStream(val);
            System.out.println("File is their");
        }
    }

//    void read() throws FileNotFoundException {
//
//        System.out.println("found file");
//        XSSFWorkbook workbook = new XSSFWorkbook(file);
//        System.out.println("in workbook");
//        XSSFSheet sheet = workbook.getSheet("Shipments");
//        System.out.println("got sheet");
//    }
}
