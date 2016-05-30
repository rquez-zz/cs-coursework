import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaterTreatmentPlantConfiguration {

    final static String CONFG_FILE = "config.txt";
    final static String OUTPUT_FILE = "output.txt";
    final static boolean WRITE_TO_FILE = true;
    final static int SLEEP_MIN = 5;
    final static int SLEEP_MAX = 10;

    private final Integer numStations;
    private final Integer numPipes;
    private final List<Integer> workLoads;

    public WaterTreatmentPlantConfiguration() throws IOException {
        this.workLoads = readConfiguration();
        this.numStations = workLoads.size();
        this.numPipes = workLoads.size();
    }

    private static List<Integer> readConfiguration() throws IOException {
        // Read file as a stream
        Stream<String> stream = Files.lines(Paths.get(CONFG_FILE));
        List<Integer> config = stream.map(Integer::parseInt).collect(Collectors.toList());
        // Remove the amount of stations
        config.remove(0);
        return config;
    }

    public Integer getNumStations() {
        return numStations;
    }

    public Integer getNumPipes() {
        return numPipes;
    }

    public List<Integer> getWorkLoads() {
        return workLoads;
    }
}
