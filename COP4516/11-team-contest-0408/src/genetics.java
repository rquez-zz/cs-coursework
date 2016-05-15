import java.util.HashMap;
import java.util.Scanner;

public class genetics {

    private static int T = 3;
    private static int G = 2;
    private static int C = 1;
    private static int A = 0;

    private static HashMap<Character, Integer> charToInt = new HashMap<>();
    private static HashMap<Integer, Character> intToChar = new HashMap<>();

    public static void main(String[] args) {

        charToInt.put('T', T);
        charToInt.put('G', G);
        charToInt.put('C', C);
        charToInt.put('A', A);
        intToChar.put(A, 'A');
        intToChar.put(G, 'G');
        intToChar.put(C, 'C');
        intToChar.put(T, 'T');

        Scanner scanner = new Scanner(System.in);

        int numCases = scanner.nextInt();

        for (int n = 0; n < numCases; n++) {

            String s = scanner.next();

            String result = null;
            if (Character.isDigit(s.charAt(0))) {
                result = decimalToGenetic(s);
            } else {
                result = genetic2Decimal(s);
            }

            System.out.println("Sequence #" + (n+1) + ": " + result);
        }
    }

    private static String decimalToGenetic(String s) {
        int decimal = Integer.parseInt(s);

        StringBuilder sb = new StringBuilder();
        while(decimal >= 1) {
            sb.append(intToChar.get(decimal % 4));
            decimal /= 4;
        }

        sb.reverse();

        return sb.toString();
    }

    private static String genetic2Decimal(String s) {
        char[] geneticChar = s.toCharArray();

        long sum = 0;
        for (int i = geneticChar.length - 1; i >= 0; i--) {
            if (geneticChar[i] != 'A') {
                long multipiler = (long)Math.pow(4, (geneticChar.length - (i + 1)));
                sum += charToInt.get(geneticChar[i]) * multipiler;
            }
        }

        return "" + sum;
    }
}
