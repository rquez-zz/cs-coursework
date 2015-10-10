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

        String[][] triangeTable = new String[input.length()][];

        // l creates the table rows
        // k increments so it shortens the table row
        // i increments for each element of table row
        int k = 0;
        for (int l = 0; l < input.length() - k; l++) {


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
