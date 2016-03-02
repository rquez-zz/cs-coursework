import java.util.*;

public class popularity {

    private static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numNetworks = scanner.nextInt();

        for (int i = 0; i < numNetworks; i++) {

            HashMap<String, HashSet<String>> network = new HashMap<>();

            // Add users
            int numPeople = scanner.nextInt();
            for (int j = 0; j < numPeople; j++) {
                network.put(scanner.next(), new HashSet<String>());
            }

            // Build Friend Connections
            int numConnections = scanner.nextInt();
            for (int j = 0; j < numConnections; j++) {
                String friend1 = scanner.next();
                String friend2 = scanner.next();
                network.get(friend1).add(friend2);
                network.get(friend2).add(friend1);
            }

            // Get Rivals and calculate coolness
            int numRivals = scanner.nextInt();
            LinkedHashMap<String, Integer> rivals = new LinkedHashMap<String, Integer>();
            for (int j = 0; j < numRivals; j++) {
                String rival = scanner.next();
                if (!network.containsKey(rival)) {
                    rivals.put(rival, 0);
                    continue;
                }
                boolean isCooler = false;
                for (int k = 1; k <= network.size() && !isCooler; k++) {
                    visited.add(rival);
                    int rivalCoolness = getCoolness(network, rival, k);
                    visited.clear();
                    visited.add("You");
                    int yourCoolness = getCoolness(network, "You", k);
                    visited.clear();
                    if (yourCoolness > rivalCoolness) {
                        rivals.put(rival, k);
                        isCooler = true;
                    }
                }
                if (!isCooler) {
                    rivals.put(rival, -1);
                }
            }

            System.out.println("Social Network " + (i+1) + ":");
            for (Map.Entry<String, Integer> e : rivals.entrySet()) {
                if (e.getValue() == -1)
                    System.out.println(e.getKey() + " is just too cool");
                else
                    System.out.println(e.getKey() + " " + e.getValue());
            }
            System.out.println();
        }
    }

    public static int getCoolness(HashMap<String, HashSet<String>> network, String friend, int k) {

        if (k == 0 ||network.get(friend).size() == 0) {
            return 0;
        }

        int coolness = 0;
        for (String f : network.get(friend)) {
            if (!visited.contains(f)) {
                visited.add(f);
                coolness += 1 + getCoolness(network, f, k-1);
            }
        }
        return coolness;
    }
}
