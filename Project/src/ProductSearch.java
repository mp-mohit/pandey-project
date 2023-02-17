import java.util.Scanner;

public class ProductSearch {

    public static void main(String[] args) {
        // read input parameters
        Scanner scanner = new Scanner(System.in);
//        while (true){
        System.out.println("Enter color of T-shirt: ");
        String color = scanner.nextLine();
        System.out.println("Enter size of T-shirt: ");
        String size = scanner.nextLine();
        System.out.println("Enter gender for T-shirt (M/F): ");
        String gender = scanner.nextLine();
        System.out.println("Enter output preference (Price/Rating/Both): ");
        String outputPref = scanner.nextLine();
//            System.out.println("For Exit");
//            String end = scanner.nextLine();
//            if(end.equals(1)){
//                System.exit(0);
//            }
        DatabaseManager databaseManager = new DatabaseManager();
//        FileWatcher fileWatcher = new FileWatcher(5000);
        if (outputPref.equals("Price")) {
            databaseManager.search(color, size, gender, 1);
        } else if (outputPref.equals("Rating")) {
            databaseManager.search(color, size, gender, 2);
        } else if (outputPref.equals("Both")) {
            databaseManager.search(color, size, gender, 3);
        }
//        }

    }
}