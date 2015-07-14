/**
 * Ricardo Vasquez
 * CasearCipher.java
 *
 */
public class CaesarCipher {


    private static final double[] FREQUENCY_TABLE = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4,
            6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

    /**
     * Converts a lower-case letter in the range a to z into the corresponding natural number in the range 0 to 25
     * @param char letter
     * @return natural number
     */
    static int let2nat(char c)
    {
        return ((int) c) - 96;
    }

    /**
     * Performs the inverse method to let2nat
     * @param int char as natural number
     * @return char
     */
    static char nat2let(int code)
    {
        return (char) (code + 96);
    }

    /**
     * Applies a shift factor in the range 0 to 25 to a lower-case letter in the range ’a’ to ’z’.
     * Characters outside this range, such as upper-case letters and punctuation, should be returned unshifted.
     * @param shftAmt amount to shift
     * @param c character to shift from
     * @return character shftAmnt away from c
     */
    static char shift(int shftAmt, char c)
    {
        int code = let2nat(c);
        if (code < 27 && code > 0)
        {
            int shiftCode = (code + shftAmt);
            if (shiftCode > 26)
                shiftCode = shiftCode % 26;
            if (shiftCode < 1)
                shiftCode += 26;
            return nat2let(shiftCode);
        }
        else
            return c;
    }

    /**
     * Encodes a string using a given shift factor
     * @param shftAmt amount to shift
     * @param str string to encode
     * @return encoded string
     */
    static String encode(int shftAmt, String str)
    {
        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++)
            strArray[i] = shift(shftAmt, strArray[i]);
        return new String(strArray);
    }

    /**
     * Performs the inverse method to encode
     * @param shftAmt amount to shift
     * @param str string to decode
     * @return decoded string
     */
    static String decode(int shftAmt, String str)
    {
        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++)
            strArray[i] = shift((-1 * shftAmt), strArray[i]);
        return new String(strArray);
    }

    /**
     * Calculates the number of lower-case letters in a string
     * @param str string to count letters in
     * @return number of letters in string
     */
    static int lowers(String str)
    {
        char[] strArray = str.toCharArray();
        int count = 0;
        for (int i = 0; i < strArray.length; i++)
        {
            int letterCode = let2nat(strArray[i]);
            if (letterCode > 0 && letterCode < 27)
               count++;
        }
        return count;
    }

    /**
     * Calculates the number of a given character in a string
     * @param str string to look in
     * @return count of character occurance in string
     */
    static int count(char c, String str)
    {
        int cLetterCode = let2nat(c);
        char[] strArray = str.toCharArray();
        int count = 0;
        for (int i = 0; i < strArray.length; i++)
        {
            if (cLetterCode == let2nat(strArray[i]))
                count++;
        }
        return count;
    }

    double percent(int num1, int num2)
    {
        return 0.0;
    }

    double[] freqs(String str)
    {
        return null;
    }

    double[] rotate(int n, double[] list)
    {
        return null;
    }

    double chisqr(double[] os)
    {

        return 0.0;
    }

    int position(double a, double[] list)
    {
        return 0;
    }

    String crack(String str)
    {
        return "";
    }


}
