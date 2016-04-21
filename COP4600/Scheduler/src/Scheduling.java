import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Ricardo Vasquez
 * COP4600
 */
public class Scheduling {

    private static final String FCFS = "fcfs";
    private static final String SJF = "sjf";
    private static final String RR = "rr";

    // For logging purposes
    private static final int ARRIVED = 0;
    private static final int SELECTED = 1;
    private static final int FINISHED = 2;
    private static final int IDLE = 3;
    private static final int END = 4;

    // For sorting processes in FCFS and Round Robin
    private static final Comparator<FakeProcess> compareByArrivalTime = new Comparator<FakeProcess>() {
        @Override
        public int compare(FakeProcess o1, FakeProcess o2) {
            return o1.getArrivalTime() - o2.getArrivalTime();
        }
    };

    // For sorting processes in SJF
    private static final Comparator<FakeProcess> compareByBurstTime = new Comparator<FakeProcess>() {
        @Override
        public int compare(FakeProcess o1, FakeProcess o2) {
            return o1.getBurstTime() - o2.getBurstTime();
        }
    };

    // For sorting processes by their ID
    private static final Comparator<FakeProcess> compareById = new Comparator<FakeProcess>() {
        @Override
        public int compare(FakeProcess o1, FakeProcess o2) {
            return o1.getId().hashCode() - o2.getId().hashCode();
        }
    };

    // Where the log appending happens
    private static void logger(StringBuilder log, int time, String id, int burstTime, int op) {

        switch (op) {
            case ARRIVED:
                log.append("Time " + time + ": " + id + " arrived\n");
                break;
            case SELECTED:
                log.append("Time " + time + ": " + id + " selected (burst " + burstTime + ")\n");
                break;
            case FINISHED:
                log.append("Time " + time + ": " + id + " finished\n");
                break;
            case IDLE:
                log.append("Time " + time + ": IDLE\n");
                break;
            case END:
                log.append("Finished at time " + time + "\n\n");
        }
    }

    public static void firstComeFirstServe(ArrayList<FakeProcess> processes, int timeUnits, StringBuilder log) {

        log.append("Using First Come First Served\n\n");

        // Sort by Arrival Time
        Collections.sort(processes, compareByArrivalTime);

        ArrayDeque<FakeProcess> queue = new ArrayDeque<>();
        ArrayList<FakeProcess> terminated = new ArrayList<>();
        queue.addAll(processes);

        // Get first process
        FakeProcess currentProcess = queue.poll();
        logger(log, 0, currentProcess.getId(), 0, ARRIVED);
        logger(log, 0, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);

        int processEndtime = currentProcess.getBurstTime();

        // Time loop
        for (int t = 0; t <= timeUnits; t++) {

            // Check if a process is arriving at this time
            for (FakeProcess process : queue) {
                if (t == process.getArrivalTime()) {
                    logger(log, t, process.getId(), 0, ARRIVED);
                }
            }

            // When time has reached the current process's end time
            if (t >= processEndtime) {

                // End process
                logger(log, t, currentProcess.getId(), 0, FINISHED);

                // Add process to queue of terminated processes
                currentProcess.setTurnaround(t - currentProcess.getArrivalTime());
                terminated.add(currentProcess);

                // If there's another ready process, execute that
                // Otherwise go idle
                if (!queue.isEmpty()) {
                    currentProcess = queue.poll();
                    logger(log, t, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);
                    currentProcess.setWait(t - currentProcess.getArrivalTime());
                    currentProcess.setTurnaround(currentProcess.getWait() + currentProcess.getBurstTime());
                    processEndtime = t + currentProcess.getBurstTime();
                } else if (t != processEndtime)
                    logger(log, t, null, 0, IDLE);
            }
        }

        logger(log, timeUnits, null, 0, END);

        // Recap scheduler results
        Collections.sort(terminated, compareById);
        for (FakeProcess process : terminated) {
            log.append(process.getId() + " wait " + process.getWait() + " turnaround " + process.getTurnaround() + "\n");
        }
    }

    public static void preemptiveShortestJobFirst(ArrayList<FakeProcess> processes, int timeUnits, StringBuilder log) {

        log.append("Using Shortest Job First (Pre)\n\n");

        // Sort by Burst Time
        Collections.sort(processes, compareByArrivalTime);

        ArrayList<FakeProcess> ready = new ArrayList<>();
        ArrayList<FakeProcess> terminated = new ArrayList<>();

        // Get First Process
        FakeProcess currentProcess = processes.remove(0);
        logger(log, 0, currentProcess.getId(), 0, ARRIVED);
        logger(log, 0, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);

        int processEndtime = currentProcess.getBurstTime();

        // Time Loop
        for (int t = 0; t <= timeUnits; t++) {

            // Check if a process is arriving at this time
            for (FakeProcess process : processes) {
                if (t == process.getArrivalTime()) {

                    // Add process to ready queue
                    ready.add(process);

                    // Re-sort by Burst Time
                    Collections.sort(ready, compareByBurstTime);

                    logger(log, t, process.getId(), 0, ARRIVED);
                }
            }


            // Check if there's a shorter job
            if (!ready.isEmpty() && ready.get(0).getBurstTime() < currentProcess.getBurstTime()) {

                // Update Burst time and put back in to ready queue
                currentProcess.setBurstTime(processEndtime - t);
                currentProcess.setLastEnd(t);
                ready.add(currentProcess);

                // Get shortest job
                currentProcess = ready.remove(0);
                processEndtime = t + currentProcess.getBurstTime();

                logger(log, t, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);
            }


            // When time has reached the current process's end time
            if (t >= processEndtime) {

                if (currentProcess != null) {
                    // End process
                    logger(log, t, currentProcess.getId(), 0, FINISHED);

                    // Add process to queue of terminated processes
                    currentProcess.setTurnaround(t - currentProcess.getArrivalTime());
                    terminated.add(currentProcess);
                }

                // If there's another ready process, execute that
                // Otherwise idle
                if (!ready.isEmpty()) {

                    // Get the next shortest job
                    currentProcess = ready.remove(0);

                    // Update wait time
                    if (currentProcess.getLastEnd() == 0)
                        currentProcess.setWait(t - currentProcess.getArrivalTime());
                    else
                        currentProcess.setWait(t - currentProcess.getLastEnd() + currentProcess.getWait());

                    // Update end time for this process
                    processEndtime = t + currentProcess.getBurstTime();

                    logger(log, t, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);
                } else if (t < timeUnits){
                    currentProcess = null;
                    logger(log, t, null, 0, IDLE);
                }
            }
        }

        logger(log, timeUnits, null, 0, END);

        // Recap scheduler results
        Collections.sort(terminated, compareById);
        for (FakeProcess process : terminated) {
            log.append(process.getId() + " wait " + process.getWait() + " turnaround " + process.getTurnaround() + "\n");
        }
    }


    public static void roundRobin(ArrayList<FakeProcess> processes, int timeUnits, int quantum, StringBuilder log) {

        log.append("Using Round-Robin\n");
        log.append("Quantum " + quantum + "\n\n");

        // Sort by Arrival Time
        Collections.sort(processes, compareByArrivalTime);

        // Ready and terminated queue
        ArrayDeque<FakeProcess> ready = new ArrayDeque<>();
        ArrayList<FakeProcess> terminated = new ArrayList<>();

        // Get first process
        FakeProcess currentProcess = processes.remove(0);
        logger(log, 0, currentProcess.getId(), 0, ARRIVED);
        logger(log, 0, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);

        int processEndtime = currentProcess.getBurstTime();
        int runningQuantum = quantum;

        // Time loop
        for (int t = 0; t < timeUnits; t++) {

            // Check if a process is arriving at this time
            for (int i = 0; i < processes.size(); i++) {
                if (t == processes.get(i).getArrivalTime()) {
                    logger(log, t, processes.get(i).getId(), 0, ARRIVED);
                    ready.add(processes.remove(i));
                }
            }

            // Check if process has ended
            if (t >= processEndtime) {

                // Add process to queue of terminated processes
                if (currentProcess != null) {

                    // Update Turnaround and add to terminated queue
                    currentProcess.setTurnaround(t - currentProcess.getArrivalTime());
                    terminated.add(currentProcess);

                    logger(log, t, currentProcess.getId(), 0, FINISHED);
                }

                // If there's another ready process and quantum is reached, execute it
                // Otherwise go Idle
                if (!ready.isEmpty()) {

                    // Save burst time used to end the process
                    int prevBurstTime = currentProcess.getBurstTime();

                    // Get next process and update it's wait time
                    currentProcess = ready.poll();
                    currentProcess.setWait(t - currentProcess.getLastEnd() + currentProcess.getWait());

                    // Determine the end time for the process
                    processEndtime = t + currentProcess.getBurstTime();

                    logger(log, t, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);
                } else {

                    // Null the current process since there's none to grab
                    currentProcess = null;

                    logger(log, t, null, 0, IDLE);
                }
            }

            // Check if the time quantum has been reached
            if (runningQuantum == 0) {
                if (currentProcess != null) {

                    // Update Burst Time
                    currentProcess.setBurstTime(processEndtime - t);

                    // Get next process on queue, otherwise continue
                    if (!ready.isEmpty()) {

                        // Place process at end of queue
                        currentProcess.setLastEnd(t);
                        ready.add(currentProcess);

                        // Get next Process
                        currentProcess = ready.poll();

                        // Set wait time
                        if (currentProcess.getLastEnd() == 0)
                            currentProcess.setWait(t - currentProcess.getArrivalTime());
                        else
                            currentProcess.setWait(t - currentProcess.getLastEnd() + currentProcess.getWait());

                        // Determine the end time for the process
                        processEndtime = t + currentProcess.getBurstTime();
                    }

                    logger(log, t, currentProcess.getId(), currentProcess.getBurstTime(), SELECTED);
                }

                // Reset running quantum
                runningQuantum = quantum;
            }

            runningQuantum--;
        }

        logger(log, timeUnits, null, 0, END);

        // Recap scheduler results
        Collections.sort(terminated, compareById);
        for (FakeProcess process : terminated) {
            log.append(process.getId() + " wait " + process.getWait() + " turnaround " + process.getTurnaround() + "\n");
        }
    }

    public static void main(String[] args) throws Exception {

        int processCount = 0;
        int timeUnits = 0;
        int quantum = 0;
        String algorithm = null;
        ArrayList<FakeProcess> processes;
        String input = null;
        String output = "processes.out";

        if (args.length >= 1) {
            input = args[0];
        } else {
            input = "processes.in";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {

            // Read Process Count
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            st.nextToken();
            processCount = Integer.parseInt(st.nextToken());

            // Read Time Units
            line = br.readLine();
            st = new StringTokenizer(line, " ");
            st.nextToken();
            timeUnits = Integer.parseInt(st.nextToken());

            // Read Algorithm To Use
            line = br.readLine();
            st = new StringTokenizer(line, " ");
            st.nextToken();
            algorithm = st.nextToken();

            // Read Quantum if Round Robin
            if (algorithm.equals(RR)) {
                line = br.readLine();
                st = new StringTokenizer(line, " ");
                st.nextToken();
                quantum = Integer.parseInt(st.nextToken());
            } else
                br.readLine();

            // Add processes
            processes = new ArrayList<>();
            for (int i = 0; i < processCount; i++) {
                line = br.readLine();
                st = new StringTokenizer(line, " ");
                st.nextToken();
                st.nextToken();
                String id = st.nextToken();
                st.nextToken();
                int arrival = Integer.parseInt(st.nextToken());
                st.nextToken();
                int burst = Integer.parseInt(st.nextToken());

                processes.add(new FakeProcess(id, arrival, burst));
            }
        }

        StringBuilder sbLog = new StringBuilder();
        sbLog.append(processCount + " processes\n");

        // Execute Algorithm
        switch(algorithm) {
            case FCFS:
                firstComeFirstServe(processes, timeUnits, sbLog);
                break;
            case SJF:
                preemptiveShortestJobFirst(processes, timeUnits, sbLog);
                break;
            case RR:
                roundRobin(processes, timeUnits, quantum, sbLog);
                break;
            default:
                break;
        }

        // Write results to file
        Path file = Paths.get(output);
        Files.write(file, sbLog.toString().getBytes());

        // Print log
        System.out.print(sbLog.toString());
    }
}
