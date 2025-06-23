import javax.swing.*;
import java.util.Random;
import java.util.concurrent.*;

public class RadarSimulator {
    public static final double RADAR_RANGE = 1000.0;
    private final AircraftManager aircraftManager = new AircraftManager();
    private final RadarPanel radarPanel;
    private ScheduledExecutorService executor;
    private final Random random = new Random();

    public RadarSimulator(RadarPanel radarPanel) {
        this.radarPanel = radarPanel;
    }

    public void startSimulation() {
        if (executor != null && !executor.isShutdown()) return;

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            aircraftManager.updateAll(0.1);
            detectAircraft();
            radarPanel.setAircraftList(aircraftManager.getAircraftList());
            radarPanel.repaint();
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stopSimulation() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    public void addRandomAircraft() {
        double x = random.nextDouble() * 1000 - 500;
        double y = random.nextDouble() * 1000 - 500;
        double speed = 5 + random.nextDouble() * 10;
        double heading = random.nextDouble() * 360;
        double stealth = random.nextDouble();
        Aircraft.TargetType type = Aircraft.TargetType.values()[random.nextInt(3)];

        Aircraft ac = new Aircraft(x, y, speed, heading, stealth, type);
        aircraftManager.addAircraft(ac);
    }

    public void addAircraft(Aircraft ac) {
        aircraftManager.addAircraft(ac);
    }

    private void detectAircraft() {
        for (Aircraft ac : aircraftManager.getAircraftList()) {
            double distance = ac.getDistance();
            if (distance <= RADAR_RANGE) {
                double roll = random.nextDouble();
                if (roll > ac.getStealthFactor()) {
                    DetectionLogger.logDetection(ac, distance);
                }
            }
        }
    }
}
