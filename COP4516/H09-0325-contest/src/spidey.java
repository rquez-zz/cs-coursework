import java.util.Scanner;

public class spidey {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {

            int numVertices = scanner.nextInt();
            int numEdges = scanner.nextInt();

            int graph[][] = new int[numVertices][numVertices];

            boolean[] used = new boolean[numVertices];

            boolean[] visited = new boolean[numVertices];

            boolean check1 = true;

            for (int j = 0; j < numEdges; j++) {

                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();

                if ((v1 + v2) % 2 == 0) {
                    check1 = false;
                }

                graph[v1][v2] = 1;
                graph[v2][v1] = 1;

                used[v1] = true;
                used[v2] = true;
            }

            if (!check1) {
                System.out.println("It's the end of the world!\n");
                continue;
            }

            if (!check(used)) {
                System.out.println("It's the end of the world!\n");
                continue;
            }

            dfs(graph, 0, visited);
            if (!check(visited)) {
                System.out.println("It's the end of the world!\n");
                continue;
            }

            if (check1) {
                System.out.println("Way to go, Spider-Man!\n");
            }
        }
    }

    private static boolean check(boolean[] array) {
        for (Boolean b : array) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
    private static void dfs(int[][] graph, int node, boolean[] visited) {

        visited[node] = true;

        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] > 0 && !visited[i]) {
               dfs(graph, i, visited);
            }
        }
    }
}
