import java.util.HashMap;
import java.util.Scanner;

public class countseq {

    static HashMap<String, Long> map = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            String seq = scanner.next().trim();
            String sub = scanner.next().trim();
            long out = 0;
            System.out.println(subseq(out, seq, sub));
            map.clear();
        }

    }

    private static long subseq(long out, String seq, String sub) {

        if (sub.length() == 0) {
            return out + 1;
        }

        if (seq.length() < sub.length()) {
            return out;
        }

        if (seq.charAt(0) == sub.charAt(0)) {
            long result = 0;
            String concat = seq.substring(1) + " " + sub.substring(1);
            if (map.containsKey(concat)) {
                result = map.get(concat);
            } else {
                result = subseq(0, seq.substring(1), sub.substring(1));
                map.put(concat, result);
            }

            long result2 = 0;
            concat = seq.substring(1) + " " + sub;
            if (map.containsKey(concat)) {
               result2 = map.get(concat);
            } else {
                result2 = subseq(0, seq.substring(1), sub);
                map.put(concat, result2);
            }

            return result + result2 + out;

        } else {
            String concat = seq.substring(1) + " " + sub;
            long result = 0;
            if (map.containsKey(concat)) {
               result = map.get(concat);
            } else {
               result = subseq(0, seq.substring(1), sub);
            }

            return out + result;
        }
    }
}
