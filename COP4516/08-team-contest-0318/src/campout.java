import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class campout {

    private final static int NUM_STUDENTS = 10;
    private final static int NUM_SHIFTS_PER_DAY = 6;
    private final static int NUM_DAYS = 7;
    private final static int NUM_TOTAL_SHIFTS = NUM_SHIFTS_PER_DAY * NUM_DAYS;
    private final static int NUM_GRAPH_NODES = (NUM_DAYS * NUM_SHIFTS_PER_DAY) + NUM_STUDENTS + 2;

    private final static int MIN_STUDENTS_TENTING = 3;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();

        for (int currentCase = 1; currentCase <= cases; currentCase++) {

            int[][] graph = new int[NUM_GRAPH_NODES][NUM_GRAPH_NODES];

            for (int j = 1; j <= NUM_STUDENTS; j++) {
                graph[0][j] = 20;
            }

            for (int j = NUM_STUDENTS + 1; j <= graph.length - 2; j++) {
                graph[j][graph.length - 1] = 3;
            }

            for (int j = 1; j <= NUM_STUDENTS; j++) {


                boolean[] shiftsCovered = new boolean[NUM_TOTAL_SHIFTS];
                for (int k = 0; k < shiftsCovered.length; k++) {
                    shiftsCovered[k] = true;
                }

                int busyIntervals = scanner.nextInt();

                for (int k = 0; k < busyIntervals; k++) {

                    int day = scanner.nextInt();
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();

                    int lowBound = (day-1)*6+start/4;
                    int highBound = (day-1)*6+(end+3)/4;

                    for (int m = lowBound; m < highBound; m++)
                        shiftsCovered[m] = false;
                }

                for (int k = 0; k < NUM_TOTAL_SHIFTS; k++) {
                    if (shiftsCovered[k]) {
                        graph[j][NUM_STUDENTS + 1 + k] = 1;
                    }
                }

            }

            NetworkFlow network = new NetworkFlow(graph, 0, graph.length - 1);
            int flow = network.getMaxFlow();

            if (flow == NUM_TOTAL_SHIFTS * MIN_STUDENTS_TENTING) {
                System.out.println("Case #"  + (currentCase) + ": YES\n");
            } else {
                System.out.println("Case #"  + (currentCase) + ": NO\n");
            }
        }
    }
}

class NetworkFlow {

    private final static int INF = Integer.MAX_VALUE;

    private FlowEdge[][] adjMat;
    private int source;
    private int dest;

    public NetworkFlow(int[][] capacities, int start, int end) {

        source = start;
        dest = end;
        adjMat = new FlowEdge[capacities.length][capacities.length];

        // Arrange adjency matrix from given flow capacities
        for (int i = 0; i< capacities.length; i++) {
            for (int j = 0; j< capacities[i].length; j++) {

                // Fill in this flow.
                if (capacities[i][j] > 0)
                    adjMat[i][j] = new FlowEdge(capacities[i][j]);
                else
                    adjMat[i][j] = null;
            }
        }
    }

    public ArrayList<FlowNode> findAugmentingPath() {

        // This will store the previous node visited in the BFS.
        FlowNode[] prev = new FlowNode[adjMat.length];
        boolean[] inQueue = new boolean[adjMat.length];

        for (int i = 0; i < inQueue.length; i++) {
            inQueue[i] = false;
        }

        prev[this.source] = new FlowNode(-1, true);

        LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.offer(new Integer(source));
        inQueue[source] = true;

        while (bfsQueue.size() > 0) {

            Integer next = bfsQueue.poll();

            for (int i = 0; i < adjMat.length; i++) {
                if (!inQueue[i] && adjMat[next][i] != null && adjMat[next][i].maxPushForward() > 0) {
                    bfsQueue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new FlowNode(next, true);
                }
            }

            for (int i=0; i<adjMat.length; i++) {
                if (!inQueue[i] && adjMat[i][next] != null && adjMat[i][next].maxPushBackward() > 0) {
                    bfsQueue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new FlowNode(next, false);
                }
            }
        }

        if (!inQueue[dest])
            return null;

        ArrayList<FlowNode> path = new ArrayList<FlowNode>();

        FlowNode place = prev[dest];

        FlowNode dummy = new FlowNode(dest, true);
        path.add(dummy);

        // Build the path backwards.
        while (place.prev != -1) {
            path.add(place);
            place = prev[place.prev];
        }

        // Reverse it now.
        Collections.reverse(path);

        return path;
    }

    public int getMaxFlow() {

        int flow = 0;

        ArrayList<FlowNode> nextpath = findAugmentingPath();

        // Loop until there are no more augmenting paths.
        while (nextpath != null) {

            // Check what the best flow through this path is.
            int this_flow = INF;
            for (int i = 0; i < nextpath.size() - 1; i++) {
                if (nextpath.get(i).forward) {
                    this_flow = Math.min(this_flow, adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].maxPushForward());
                } else {
                    this_flow = Math.min(this_flow, adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].maxPushBackward());
                }
            }

            // Now, put this flow through.
            for (int i= 0; i < nextpath.size() - 1; i++) {

                if (nextpath.get(i).forward) {
                    adjMat[nextpath.get(i).prev][nextpath.get(i+1).prev].pushForward(this_flow);
                } else {
                    adjMat[nextpath.get(i+1).prev][nextpath.get(i).prev].pushBack(this_flow);
                }
            }

            // Add this flow in and then get the next path.
            flow += this_flow;
            nextpath = findAugmentingPath();
        }

        return flow;
    }
}

class FlowEdge {

    private int capacity;
    private int flow;

    public FlowEdge(int capacity) {
        this.capacity = capacity;
        this.flow = 0;
    }

    public int maxPushForward() {
        return capacity - flow;
    }

    public int maxPushBackward() {
        return flow;
    }

    public boolean pushForward(int moreflow) {

        if (this.flow + moreflow > this.capacity)
            return false;

        flow += moreflow;

        return true;
    }

    public boolean pushBack(int lessflow) {

        if (this.flow < lessflow)
            return false;

        this.flow -= lessflow;

        return true;
    }
}

class FlowNode {

    public int prev;
    public boolean forward;

    public FlowNode(int node, boolean dir) {
        prev = node;
        forward = dir;
    }
}
