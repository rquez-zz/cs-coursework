import java.util.Scanner;

public class upwards {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();
        String[] words = new String[cases];

        for (int i = 0; i < cases; i++) {
            words[i] = scanner.next();
        }

        for (int i = 0; i < words.length; i++) {

            boolean isUpwards = true;
            for (int j = 0; j < words[i].length() - 1; j++) {
                if (words[i].charAt(j) >= words[i].charAt(j+1)) {
                    isUpwards = false;
                    break;
                }
            }

            if (isUpwards) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
