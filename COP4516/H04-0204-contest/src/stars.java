import java.util.*;

public class stars {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numSkies = scanner.nextInt();

        for (int i = 0; i < numSkies; i++) {

            int numStars = scanner.nextInt();
            int numConnections = scanner.nextInt();

            ArrayList<Edge> edges = new ArrayList<>();
            HashSet<Integer> starsUsed = new HashSet<>();

            // Build Graph
            for (int j = 0; j < numConnections; j++) {
                int fromStar = scanner.nextInt();
                int toStart = scanner.nextInt();
                edges.add(new Edge(fromStar, toStart));
                starsUsed.add(fromStar);
                starsUsed.add(toStart);
            }

            // Create a joint set
            DisjointSet disjointSet = new DisjointSet(numStars + 1);

            HashSet<Integer> constellationsThatNeedFixing = new HashSet<>();
            for (Edge e : edges) {
                if (disjointSet.find(e.from) != disjointSet.find(e.to)) {
                    if (constellationsThatNeedFixing.contains(disjointSet.find(e.to))) {
                        constellationsThatNeedFixing.remove(disjointSet.find(e.to));
                        disjointSet.union(e.from, e.to);
                        constellationsThatNeedFixing.add(disjointSet.find(e.to));
                    } else {
                        disjointSet.union(e.from, e.to);
                    }
                } else {
                    constellationsThatNeedFixing.add(disjointSet.find(e.to));
                }
            }

            int diff = numStars - starsUsed.size();
            int numConstellations = getNumConstellations(disjointSet) - diff;
            int needFixing = constellationsThatNeedFixing.size();
            String print = "Night sky #" + (i+1) + ": " + numConstellations + " constellations, of which " + needFixing + " need to be fixed.\n";
            System.out.println(print);
        }
    }

    private static int getNumConstellations(DisjointSet disjointSet) {
        int count = 0;
        for (DisjointSet.Node n : disjointSet.nodes) {
            if (n.parent == null) {
                count++;
            }
        }
        return count - 1;
    }
}

class Edge {

    int from;
    int to;

    public Edge(int v1, int v2) {
        this.from = v1;
        this.to = v2;
    }
}

class DisjointSet {

    public Node[] nodes;

    public DisjointSet(int nodeCount) {
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node();
            nodes[i].id = i;
        }
    }

    public int union(int a, int b) {
        Node repA = nodes[find(a)];
        Node repB = nodes[find(b)];
        repB.parent = repA;
        return repA.id;
    }

    public int find(int a) {
        Node n = nodes[a];
        int jumps = 0;
        while (n.parent != null) {
            n = n.parent;
            jumps++;
        }
        return n.id;
    }

    public static class Node {
        Node parent;
        int id;
    }
}
