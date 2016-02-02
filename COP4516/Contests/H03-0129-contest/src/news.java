import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class news {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numCases; i++) {

            int numEmployees = scanner.nextInt();

            // Build tree
            NewsNode root = new NewsNode(0);
            for (int j = 1; j < numEmployees; j++) {

                int supervisor = scanner.nextInt();
                NewsNode subordinate = new NewsNode(j);

                NewsNode currentSupervisor = getSupervisor(root, supervisor);
                currentSupervisor.subordinates.add(subordinate);
            }

            int rounds = getChildren(root);
            System.out.println(rounds);
        }
    }

    public static NewsNode getSupervisor(NewsNode node, int number) {

        if (node == null) {
            return null;
        }

        if (node.number == number) {
            return node;
        }

        for (NewsNode n : node.subordinates) {
            NewsNode supervisor = getSupervisor(n, number);
            if (supervisor != null) {
                return supervisor;
            }
        }

        return null;
    }

    public static int getChildren(NewsNode newsNode) {

        if (newsNode.subordinates.size() == 0) {
            return 0;
        }

        ArrayList<Integer> counts = new ArrayList<>();
        for (NewsNode n : newsNode.subordinates) {
            counts.add(getChildren(n));
        }

        Collections.sort(counts, Collections.reverseOrder());

        for (int i = 0; i < counts.size(); i++) {
            counts.set(i, counts.get(i) + (i+1));
        }

        return Collections.max(counts);
    }
}

class NewsNode implements Comparable{

    int number;
    int count;
    ArrayList<NewsNode> subordinates;

    public NewsNode(int number) {
        this.number = number;
        this.subordinates = new ArrayList<NewsNode>();
    }

    @Override
    public int compareTo(Object o) {
        return this.count < ((NewsNode)o).count ? 1 : -1;
    }
}
