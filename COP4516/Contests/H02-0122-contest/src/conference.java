import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class conference {

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        int numCases= scanner.nextInt();
        event[][] cases = new event[numCases][];

        for (int i = 0; i < cases.length; i++) {

            int numOffers = scanner.nextInt();

            List<event> events = new ArrayList();

            for (int j = 0; j < numOffers; j++) {
                events.add(new event(scanner.nextInt(), scanner.nextInt()));
            }

            Collections.sort(events);
            cases[i] = events.toArray(new event[events.size()]);
        }

        for (int i = 0; i < cases.length; i++) {

            List<event> schedule = new ArrayList<>();
            schedule.add(cases[i][0]);
            int currentEvent = 0;
            for (int j = 1; j < cases[i].length; j++) {

                int end = cases[i][currentEvent].start + cases[i][currentEvent].length;
                if (end <= cases[i][j].start) {
                    currentEvent = j;
                    schedule.add(cases[i][j]);
                }
            }

            int revenue = 0;
            for (int j = 0; j < schedule.size(); j++) {
                int end = schedule.get(j).start + schedule.get(j).length;
                for (int k = schedule.get(j).start; k < end; k++)
                    revenue += (int)Math.pow(2, 29 - k);
            }

            System.out.println(revenue);
        }
    }
}

class event implements Comparable{

    int start;
    int length;

    public event (int start, int length) {
        this.start = start;
        this.length = length;
    }

    @Override
    public int compareTo(Object o) {
        if (start == ((event)o).start)
            return 0;
        return start > ((event)o).start ? 1 : -1;
    }
}
