import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class FileWatcher implements Runnable {


private String directoryPath; // configurable directory path
private int refreshInterval; // configurable time interval for checking new or updated files
private boolean running; // flag to indicate if thread is running

public FileWatcher(int refreshInterval) {
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
            DatabaseManager databaseManager = new DatabaseManager();
        } catch (InterruptedException e) {
            System.out.println("File watcher thread interrupted.");
        }


    }

    System.out.println("File watcher thread stopped.");
}

public void stop() {
    running = false;
}
}