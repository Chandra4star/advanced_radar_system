import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AircraftManager {
    private final List<Aircraft> aircraftList = new CopyOnWriteArrayList<>();

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void addAircraft(Aircraft ac) {
        aircraftList.add(ac);
    }

    public void updateAll(double deltaTime) {
        for (Aircraft ac : aircraftList) {
            ac.move(deltaTime);
        }
    }
}
