import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Vasquez on 10/10/15.
 *
 * Hill matrix of size 2x2 to 9x9
 * First argument is the encryption key file
 * Second argument is the name of the file to be encrypted
 */
public class hillcipher {

    int[][] key;

    public hillcipher(int[][] key) {
        this.key = key;
    }

    public static int[][] readKeyFile(String path) {

        int[][] key;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            int size = Integer.parseInt(br.readLine());
            key = new int[size][size];
            for (int i = 0; i < size; i++) {
                String[] line = br.readLine().split(" ");
                for (int j = 0; j < size; j++) {
                    key[i][j] = Integer.parseInt(line[j]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        for (int[] a : key) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return key;
    }

    public ArrayList<Character> encryptFile(String path, int keySize) {

        ArrayList<Character> plainText = new ArrayList<>();
        ArrayList<Character> cipherText = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String data = "";
            while ((data = br.readLine()) != null) {
                System.out.println(data);
                ArrayList<Character> line = new ArrayList<Character>();
                for (Character c : data.toCharArray()) {
                    if (Character.isAlphabetic(c)) {
                        line.add(Character.toLowerCase(c));
                    }
                }
                plainText.addAll(line);
            }

        } catch (IOException e) {
            throw new RuntimeException("IOException occured, " + e.getMessage());
        }

        while (!plainText.isEmpty()) {
            List<Character> subplainText;
            if (plainText.size() >= keySize)
                subplainText = plainText.subList(0, keySize);
            else {
                subplainText = plainText.subList(0, plainText.size());
            }
            cipherText.addAll(encryptCharacters(subplainText, keySize));
            for (Character c : subplainText.toArray(new Character[subplainText.size()])) {
                plainText.remove(c);
            }
        }

        return cipherText;
    }

    public List<Character> encryptCharacters(List<Character> characters, int keySize) {


        while (characters.size() != keySize)
            characters.add('x');

        ArrayList<Character> ciphers = new ArrayList<>();

        for (int i = 0; i < key.length; i++) {
            int j = 0;
            int sum = 0;
            for (Character c : characters) {
                sum += key[i][j] * (Character.getNumericValue(c) - 10);
                j++;
            }
            ciphers.add(Character.forDigit( (sum % 26) + 10, Character.MAX_RADIX));
        }
        return ciphers;
    }

    public static void main(String[] args) {

        String keyFilePath = args[0];

        int[][] key = readKeyFile(keyFilePath);
        hillcipher h = new hillcipher(key);

        ArrayList<Character> cipher = h.encryptFile(args[1], key.length);

        int i = 1;
        for (Character c : cipher) {
            if (i < 80)
                System.out.print(c);
            else {
                System.out.println(c);
                i = 0;
            }
            i++;
        }
        System.out.println();
    }
}
