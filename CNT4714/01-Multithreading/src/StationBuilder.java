import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: StationBuilder
 */
public class StationBuilder {

    private final static String CONFG_FILE = "config.txt";

    /**
     * Builds the stations with their assigned pipes
     * from the given config file
     * @return
     * @throws IOException
     */
    public static List<Station> buildDefaultStations() throws IOException {
        // Read file as a stream
        try (Stream<String> stream = Files.lines(Paths.get(CONFG_FILE))) {
            List<Integer> input = stream.map(Integer::parseInt).collect(Collectors.toList());

            int numStations = input.remove(0);

            List<Station> stations = new ArrayList<>();
            for (int i = 0; i < numStations; i++) {
                stations.add(new Station(input.get(i), i));
            }

            return stations;
        } catch (IOException e) {
            // Pass exception to main class
            throw e;
        }
    }
}