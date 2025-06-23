import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DetectionLogger {
    private static final String LOG_FILE = "detections.log";

    public static void logDetection(Aircraft ac, double distance) {
        String logEntry = String.format("[%s] Detected %s at %.2f m (stealth %.2f)\n",
                LocalDateTime.now(), ac.getType(), distance, ac.getStealthFactor());
        System.out.print(logEntry);

        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
