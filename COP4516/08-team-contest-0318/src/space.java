import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class space {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int steven = 1;
        PrintStream ps = new PrintStream(System.out);

        while(true) {

            HashMap<Integer, Integer> a = getPrimes(sc.nextInt());
            HashMap<Integer, Integer> b = getPrimes(sc.nextInt());

            if (a == null && b == null) break;

            for (Integer i : a.keySet()) {
                if (!b.containsKey(i)) {
                    b.put(i, 0);
                }
            }

            for (Integer i : b.keySet()) {
                if (!a.containsKey(i)) {
                    a.put(i, 0);
                }
            }

            int out = 0;

            for (Integer i : a.keySet()) {
                out += Math.abs(a.get(i) - b.get(i));
            }

            ps.printf("%d. %d:%d\n", steven, a.size(), out);
            steven++;
        }
    }

    private static HashMap<Integer,Integer> getPrimes(int a) {

        HashMap<Integer, Integer> map = new HashMap<>();

        if (a == 0) return null;

        for (int i = 2; i <= a; i++) {
            while (a % i == 0) {
                a /= i;
                if (map.containsKey(i)) {
                    map.put(i, map.get(i)+1);
                    //System.out.println("PUTTING+INCREMENT ! KEY " + i + " VALUE: " + map.get(i));
                } else {
                    map.put(i, 1);
                    //System.out.println("PUTTING ! KEY " + i + " VALUE: " + map.get(i));
                }
            }
        }
        return map;
    }
}
