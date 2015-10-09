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

            String[] tableRow = new String[input.length() - k];
            int i = 0;

            for (Character c : input.toCharArray()) {

                String set = new String();

                for (String[] rule : rules) {

                    // j goes through each generated symbol
                    for (int j = 1; j < rule.length; j++) {

                        if (rule[j].equals(c.toString())) {

                            set += rule[0];
                        }
                    }
                }

                tableRow[i] = set;
                i++;
            }

            triangeTable[k] = tableRow;
            k++;
        }

        return false;
    }
}
