import java.util.Scanner;

public class paintme {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numApartments = scanner.nextInt();
        int width = scanner.nextInt();
        int length = scanner.nextInt();
        int height = scanner.nextInt();
        int m = scanner.nextInt();
        int numWindowsAndDoors = scanner.nextInt();

        while (numApartments != 0
                || width != 0
                || length != 0
                || height != 0
                || m != 0
                || numWindowsAndDoors != 0) {

            int wallX = width * height;
            int wallY = length * height;
            int ceiling = width * length;
            int area = ((wallX + wallY) * 2) + ceiling;

            for (int i = 0; i < numWindowsAndDoors; i++) {
                int w = scanner.nextInt();
                int h = scanner.nextInt();
                area -= w * h;
            }

            area *= numApartments;

            System.out.println((int)Math.ceil((double)area/m ));

            numApartments = scanner.nextInt();
            width = scanner.nextInt();
            length = scanner.nextInt();
            height = scanner.nextInt();
            m = scanner.nextInt();
            numWindowsAndDoors = scanner.nextInt();
        }
    }
}
