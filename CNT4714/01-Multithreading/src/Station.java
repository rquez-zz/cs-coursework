/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: Station
 * Description: Abstraction of a station and its properties
 */
public class Station {

    // The number of times that a station needs to have
    // exclusive access to the input and output pipes
    private int workload;

    // The station's specified number
    private int stationNumber;

    // The pipe that inflows into the station
    private Pipe inPipe;

    // The pipe number the flows out from the station
    private Pipe outPipe;

    public Station(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public Pipe getInPipe() {
        return inPipe;
    }

    public void setInPipe(Pipe inPipe) {
        this.inPipe = inPipe;
    }

    public Pipe getOutPipe() {
        return outPipe;
    }

    public void setOutPipe(Pipe outPipe) {
        this.outPipe = outPipe;
    }

    public int getStationNumber() {
        return stationNumber;
    }
}
