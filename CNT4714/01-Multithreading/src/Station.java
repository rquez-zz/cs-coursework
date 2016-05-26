/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 1 – Multi-threaded programming in Java
 * Date: May 31, 2016
 * Class: Station
 */
public class Station implements Runnable {

    // The number of times that a station needs to have
    // exclusive access to the input and output pipes
    private int workload;

    // The station's specified number
    private int stationNumber;

    // The pipe number that inflows into the station
    private int inPipe;

    // The pipe number the flows out from the station
    private int outPipe;

    /**
     * Each station must have a specified workload, station number, and which
     * pipes it connects to.
     * @param workload
     * @param stationNumber
     * @param inPipe
     * @param outPipe
     */
    public Station(int workload, int stationNumber, int inPipe, int outPipe) {
        this.workload = workload;
        this.stationNumber = stationNumber;
        this.inPipe = inPipe;
        this.outPipe = outPipe;
    }

    public void output() {

    }

    public void input() {

    }

    public void doWork() {

    }

    @Override
    public void run() {

    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }
}
