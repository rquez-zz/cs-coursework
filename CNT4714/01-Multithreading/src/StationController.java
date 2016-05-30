import java.util.List;
import java.util.Random;

/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: StationController
 * Description: Executes Station operations on a thread
 */
public class StationController implements Runnable {

    // Station to be managed
    private final Station station;

    // List of all pipes
    private final List<Pipe> pipes;

    // The station's workload to be set
    private final Integer workload;

    private final static Random random = new Random();

    public StationController(Station station, List<Pipe> pipes, Integer workload) {
        this.station = station;
        this.pipes = pipes;
        this.workload = workload;
    }

    @Override
    public void run() {

        // Set In Connection
        if (station.getStationNumber() == 0) {
            station.setInPipe(pipes.get(pipes.size() - 1));
        } else {
            station.setInPipe(pipes.get(station.getStationNumber() - 1));
        }
        StationLogger.logInputConnection(station.getStationNumber(), station.getInPipe().getNumber());

        // Set Out Connection
        station.setOutPipe(pipes.get(station.getStationNumber()));
        StationLogger.logOutputConnection(station.getStationNumber(), station.getOutPipe().getNumber());

        // Set Workload
        station.setWorkload(workload);
        StationLogger.logWorkload(station.getStationNumber(), workload);

        while (station.getWorkload() > 0) {
            doWork();

            try {
                int time = random.ints(WaterTreatmentPlantConfiguration.SLEEP_MIN,
                        WaterTreatmentPlantConfiguration.SLEEP_MAX).findFirst().getAsInt();
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Complete workload
        StationLogger.logCompleteWorkload(station.getStationNumber());
    }

    private void doWork() {

        // Get access to In pipe
        if (station.getInPipe().getOwner() == Pipe.NONE) {
            StationLogger.logGrantAccess(station.getStationNumber(), station.getInPipe().getNumber());
            station.getInPipe().setOwner(station);
        }

        // Get access to out pipe
        if (station.getOutPipe().getOwner() == Pipe.NONE) {
            StationLogger.logGrantAccess(station.getStationNumber(), station.getOutPipe().getNumber());
            station.getOutPipe().setOwner(station);
        }

        // If both pipes are controlled, do flow work
        if (station.getInPipe().getOwner() == station && station.getOutPipe().getOwner() == station) {
            StationLogger.logFlowSuccess(station.getStationNumber(), station.getInPipe().getNumber());
            StationLogger.logFlowSuccess(station.getStationNumber(), station.getOutPipe().getNumber());
            station.setWorkload(station.getWorkload() - 1);
        }

        // Release in pipe
        if (station.getInPipe().getOwner() == station) {
            StationLogger.logGrantRelease(station.getStationNumber(), station.getInPipe().getNumber());
            station.getInPipe().setOwner(Pipe.NONE);
        }
        // Release out pipe
        if (station.getOutPipe().getOwner() == station) {
            StationLogger.logGrantRelease(station.getStationNumber(), station.getOutPipe().getNumber());
            station.getOutPipe().setOwner(Pipe.NONE);
        }
    }
}
