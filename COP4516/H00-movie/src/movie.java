import java.util.Scanner;

public class movie {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        for (int i = 0; i < testCases; i++) {
            int numTickets = scanner.nextInt();
            int numSlices = scanner.nextInt();
            int slicePrice = 2;
            if (numTickets >= 10) slicePrice = 1;
            System.out.println((numSlices * slicePrice) + (10 * numTickets));
        }
    }
}
