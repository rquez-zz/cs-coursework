/**
 * Ricardo Vasquez
 * CasearCipher.java
 *
 */
public class CaesarCipher {


    private static final double[] EXPECTED_FREQUENCY_TABLE = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4,
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

    /**
     * Calculates the percentage of one integer with respect to another, returning the result as a float
     * @param num1 numerator
     * @param num2 denominator
     * @return float percent
     */
    static double percent(int num1, int num2)
    {
        return ((double) num1 / (double) num2) * 100;
    }

    /**
     *  Returns the list of percentage frequencies of each of the lower-case letters 'a' to 'z' in a string
     * @param str string to analyze
     * @return list of percentages
     */
    static double[] freqs(String str)
    {
        double[] freqs = new double[26];
        char[] strArray = str.toCharArray();
        for (int i = 0; i < freqs.length; i++)
            freqs[i] = percent(count(nat2let(i + 1), str), str.length());
        return freqs;
    }

    /**
     * Rotates a list n places to the left assuming n is in the range zero to the length of the list
     * @param n places to rotates left
     * @param list list to rotate
     * @return rotated list
     */
    static double[] rotate(int n, double[] list)
    {
        for(int i = 0; i < n; i++)
        {
            double temp = list[0];
            for (int j = 0; j < list.length - 1; j++)
            {
                list[j] = list[j+1];
            }
            list[list.length - 1] = temp;
        }
        return list;
    }

    /**
     * Calculates the chi square statistic for a list of observed frequencies
     * with respect to a list of expected frequencies es, which is given by
     * sum(0, n-1, ((os[i] - es[i])^2 / es[i] ))
     * The expected frequencies is EXPECTED_FREQUENCY_TABLE;
     * @param os observed frequencies
     * @return chi square statistic
     */
    static double chisqr(double[] os)
    {
        double sum = 0.0;
        for (int i = 0; i < os.length; i++)
            sum += Math.pow((os[i] - EXPECTED_FREQUENCY_TABLE[i]), 2) / EXPECTED_FREQUENCY_TABLE[i];
        return sum;
    }

    /**
     *  Returns the position at which a value occurs in a list, assuming that it occurs at least once
     * @param a value to look for
     * @param list list to look in
     * @return index of value
     */
    static int position(double a, double[] list)
    {
        for (int i = 0; i < list.length; i++)
        {
            if (list[i] == a)
                return i;
        }
        return -1;
    }

    static double findMinimum(double[] list)
    {
        double min = list[0];
        for (int i = 1; i < list.length; i++)
        {
            if (min > list[i])
                min = list[i];
        }
        return min;
    }

    /**
     *  Attempts to decode at string by first calcluating the letter frequencies in the string, then calculating the
     *  chi square value of each rotation (in the range zero to twenty-five) of this list with respect to the table of
     *  expected frequencies, and finally using the position of the minimum value in this list as the shift factor to
     *  decode the original string.
     * @param str string to decode
     * @return decoded string
     */
    static String crack(String str)
    {
        double[] chisqrValues = new double[26];
        for (int i = 0; i < chisqrValues.length; i++)
            chisqrValues[i] = chisqr(rotate(i, freqs(str)));

        int shiftAmt = position(findMinimum(chisqrValues), chisqrValues);
        return decode(shiftAmt, str);
    }


}
