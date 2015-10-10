import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by ricardo on 10/8/15.
 */
public class Chomsky {
    /**
     * Each array is a rule.
     * The first element of each array is the variable for that rule
     * The subsequent elements are possible variables and terminals.
     */
    private final String[][] rules;

    public Chomsky(String[][] rules) {
        this.rules = rules;
    }

    boolean isInGrammar(String input) {


    /**
     * Builds a triangular table where the diagonals going from left to right
     * are added for each character of the input
     *
     * @param input
     * @return
     */
    String[][] buildTriangleTable(String input) {

        String[][] triangleTable = buildFirstDiagonal(input);
        for (int i = 1; i < triangleTable.length; i++)
            triangleTable = buildDiagonal(triangleTable, i);

        return triangleTable;
    }

    /**
     * Builds the diagonal in the triangle table that starts at dIndex in the table
     *
     * @param triangleTable
     * @param dIndex
     * @return
     */
    String[][] buildDiagonal(String[][] triangleTable, int dIndex) {

        int i = 0;
        int j = dIndex;

        while (triangleTable.length - dIndex > i) {
            ArrayList<String> list = cartProductRecursive(triangleTable, i, i, j);
            String[] product = list.toArray(new String[list.size()]);
            String rules = getRulesForSymbols(product);
            triangleTable[j][i] = rules;
            i++;
            j++;
        }

        return triangleTable;
    }

    /**
     * Recursively generates the cartisian product of 2 symbols and then unions together the results
     * @param triangleTable
     * @param i
     * @param k
     * @param j
     * @return
     */
    ArrayList<String> cartProductRecursive(String[][] triangleTable, int i, int k, int j) {

        ArrayList<String> list = new ArrayList<>();

        if (k == j) {
            return list;
        }

        for (Character a : triangleTable[k][i].toCharArray()) {
            for (Character b : triangleTable[j][k+1].toCharArray()) {
                list.add(a.toString() + b.toString());
            }
        }

        list.addAll(cartProductRecursive(triangleTable, i, k+1, j));

        return list;
    }

    /**
     * Checks if an array of symbols is part of the rules
     *
     * @param symbols
     * @return
     */
    String getRulesForSymbols(String[] symbols) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < symbols.length; i++) {

            for (String[] rule : rules) {

                for (int j = 1; j < rule.length; j++) {

                    if (rule[j].equals(symbols[i])) {

                        sb.append(rule[0]);
                    }
                }
            }
        }

        //Check for duplicates
        Set<Character> seen = new HashSet<Character>();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(!seen.contains(c)) {
                seen.add(c);
            } else {
                sb.deleteCharAt(i);
                i--;
            }
        }

        // Arrange string alphabetically with S as the beginning
        String result;
        char[] cArray;
        if (sb.indexOf("S") != -1) {
            char s = sb.charAt(sb.indexOf("S"));
            sb.deleteCharAt(sb.indexOf("S"));
            result = "S";
        } else {
            result = new String();
        }
        cArray = sb.toString().toCharArray();
        Arrays.sort(cArray);
        result += String.copyValueOf(cArray);

        return false;
    }
}
