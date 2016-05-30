import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: WaterTreatmentPlant
 */
public class WaterTreatmentPlant {

    public static void main(String[] args) {

        // Read in configuration
        WaterTreatmentPlantConfiguration waterPlantConfiguration;
        try {
            waterPlantConfiguration = new WaterTreatmentPlantConfiguration();
        } catch (IOException e) {
            // Stop completely if we can't get a proper configuration
            throw new RuntimeException(e.getMessage());
        }

        // Build stations and pipes
        List<Station> stations = buildStations(waterPlantConfiguration.getNumStations());
        List<Pipe> pipes = buildPipes(waterPlantConfiguration.getNumPipes());

        // Build runnable station controllers
        List<StationController> stationControllers = new ArrayList<>();
        for (Station station : stations) {
            stationControllers.add(new StationController(
                    station,
                    pipes,
                    waterPlantConfiguration.getWorkLoads().get(station.getStationNumber())));
        }

        // Run all threads
        for (StationController controller : stationControllers) {
            (new Thread(controller)).start();
        }
    }

    public static List<Pipe> buildPipes(int numPipes) {
        List<Pipe> list = new ArrayList<>();
        for (int i = 0; i < numPipes; i++) {
            list.add(new Pipe(i));
        }
        return list;
    }

    public static List<Station> buildStations(int numStations) {
        List<Station> list = new ArrayList<>();
        for (int i = 0; i < numStations; i++) {
            list.add(new Station(i));
        }
        return list;
    }
}
