import java.util.List;

public class PipeController {
    private List<Pipe> pipes;

    public PipeController(List<Pipe> pipes) {
        this.pipes = pipes;
    }

    /**
     * The in pipe is always the index before the station number
     * @param station
     * @return
     */
    public Pipe getAssignedInPipe(Station station) {
        if (station.getStationNumber() == 0) {
            return pipes.get(pipes.size() - 1);
        } else {
            return pipes.get(station.getStationNumber() - 1);
        }
    }

    /**
     * The out pipe is always the index after the station number
     * @param station
     * @return
     */
    public Pipe getAssignedOutPipe(Station station) {
        return pipes.get(station.getStationNumber());
    }
}
