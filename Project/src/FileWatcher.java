import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class FileWatcher implements Runnable {


private String directoryPath; // configurable directory path
private int refreshInterval; // configurable time interval for checking new or updated files
private boolean running; // flag to indicate if thread is running

public FileWatcher(String directoryPath, int refreshInterval) {
    this.directoryPath = directoryPath;
    this.refreshInterval = refreshInterval;
    this.running = false;
}

@Override
public void run() {
    running = true;
    System.out.println("File watcher thread started.");

    while (running) {
        try {
            TimeUnit.SECONDS.sleep(refreshInterval); // wait for refresh interval before checking again
        } catch (InterruptedException e) {
            System.out.println("File watcher thread interrupted.");
        }

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : stream) {
                if (Files.isRegularFile(path) && path.toString().endsWith(".csv")) {
                    // load the new or updated CSV file into the database
                    System.out.println("New or updated file found: " + path.toString());
                    // TODO: load file into database
                }
            }
        } catch (Exception e) {
            System.out.println("Error while watching directory: " + e.getMessage());
        }
    }

    System.out.println("File watcher thread stopped.");
}

public void stop() {
    running = false;
}
}