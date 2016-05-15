import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class cpu {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numDesigns = scanner.nextInt();

        for (int n = 0; n < numDesigns; n++) {

            int subSystems = scanner.nextInt();
            List<wire> wires = new ArrayList<wire>();
            int[][] matrix = new int[subSystems][subSystems];

            for (int i = 0; i < subSystems; i++) {
                for (int j = 0; j < subSystems; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            for (int i = 1; i < subSystems; i++) {
                for (int j = 0; j < i; j++) {
                    wires.add(new wire(i, j, matrix[i][j]));
                }
            }

            Collections.sort(wires);
            disjoint set = new disjoint(subSystems);
            List<wire> efficientDesign = new ArrayList<>();

            for (int i = 0; i < wires.size() && efficientDesign.size() < subSystems - 1; i++) {

                wire w = wires.get(i);
                if (set.find(w.from) != set.find(w.to)) {
                    efficientDesign.add(w);
                    set.union(w.from, w.to);
                }
            }

            int sum = 0;
            for (wire w : efficientDesign) {
                sum += w.weight;
            }

            System.out.println("Design " + (n+1) + ": " + sum + " micrometers");
        }

    }
}

class wire implements Comparable<wire> {

    int weight;
    int from;
    int to;

    public wire(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(wire wire) {
        if (this.weight == wire.weight)
            return 0;
        return this.weight > wire.weight ? 1 : -1;
    }
}

class disjoint {

    private node[] nodes;

    public disjoint(int nodeCount) {
        nodes = new node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new node();
            nodes[i].id = i;
        }
    }

    public int union(int a, int b) {
        node repA = nodes[find(a)];
        node repB = nodes[find(b)];
        repB.parent = repA;
        return repA.id;
    }

    public int find(int a) {
        node n = nodes[a];
        int jumps = 0;
        while (n.parent != null) {
            n = n.parent;
            jumps++;
        }
        return n.id;
    }

    private static class node {
        node parent;
        int id;
    }
}
