import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: StationController
 * Description: Statically outputs logs of Station operations to console
 */
public class StationLogger {

    public static void logInputConnection(int station, int pipe) {
        String out = String.format("Station %d: In-Connection set to pipe %d.\n", station, pipe);
        write(out);
    }

    public static void logOutputConnection(int station, int pipe) {
        String out = String.format("Station %d: Out-Connection set to pipe %d.\n", station, pipe);
        write(out);
    }

    public static void logWorkload(int station, int workload) {
        String out = String.format("Station %d: Workload set to %d.\n", station, workload);
        write(out);
    }

    public static void logGrantAccess(int station, int pipe) {
        String out = String.format("Station %d: granted access to pipe %d.\n", station, pipe);
        write(out);
    }

    public static void logGrantRelease(int station, int pipe) {
        String out = String.format("Station %d: released access to pipe %d.\n", station, pipe);
        write(out);
    }

    public static void logCompleteWorkload(int station) {
        String out = String.format("* * Station %d: Workload successfully completed. * *\n", station);
        write(out);
    }

    public static void logFlowSuccess(int station, int pipe) {
        String out = String.format("Station %d: successfully flows on pipe %d.\n", station, pipe);
        write(out);
    }

    private static void write(String out) {
        System.out.print(out);
        if (WaterTreatmentPlantConfiguration.WRITE_TO_FILE)
            writeToFile(out);
    }
    /**
     * Writes to a file
     * @param out
     */
    private static void writeToFile(String out) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(WaterTreatmentPlantConfiguration.OUTPUT_FILE, true))) {
            writer.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
