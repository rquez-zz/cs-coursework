import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COT4210 Assignment 3
 * TM - Turing Machine
 * Author: Ricardo Vasquez
 */
class Rule {

    private int inputState;
    private char charToRead;
    private int outputState;
    private char charToWrite;
    private int direction;

    public Rule(int inputState, char charToRead, int outputState, char charToWrite, int direction) {
        this.inputState = inputState;
        this.charToRead = charToRead;
        this.outputState = outputState;
        this.charToWrite = charToWrite;
        this.direction = direction;
    }

    public int getInputState() {
        return inputState;
    }

    public void setInputState(int inputState) {
        this.inputState = inputState;
    }

    public char getCharToRead() {
        return charToRead;
    }

    public void setCharToRead(char charToRead) {
        this.charToRead = charToRead;
    }

    public int getOutputState() {
        return outputState;
    }

    public void setOutputState(int outputState) {
        this.outputState = outputState;
    }

    public char getCharToWrite() {
        return charToWrite;
    }

    public void setCharToWrite(char charToWrite) {
        this.charToWrite = charToWrite;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
public class TM {

    private Rule rules[];
    private int maxSteps;

    private final char BLANK = 'B';
    private final char SPECIAL = '#';

    public TM(Rule rules[], int maxSteps) {
        this.rules = rules;
        this.maxSteps = maxSteps;
    }

    public Rule[] getRules() {
        return rules;
    }

    public void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    /**
     * Returns array of usable rules from a given state
     * @param state
     * @return
     */
    private Rule[] getAvaliableRules(int state) {
        ArrayList<Rule> ruleList = new ArrayList();

        for (Rule rule : rules) {
            if (rule.getInputState() == state) {
                ruleList.add(rule);
            }
        }

        return ruleList.toArray(new Rule[ruleList.size()]);
    }

    /**
     * Creates an ArrayList of characters from a string
     * @param input
     * @return
     */
    private ArrayList<Character> buildTape(String input) {
        ArrayList<Character> tape = new ArrayList();
        for (Character c : input.toCharArray()) {
           tape.add(c);
        }
        return tape;
    }

    /**
     * Returns 0 if accepts, 1 if rejects, -1 if does not halt in n steps
     * @param input
     * @return 0, 1, or -1
     */
    public int run(String input) {

        ArrayList<Character> tape = buildTape(input);
        int currentState = 0;
        int head = 0;
        int steps = 0;

        // If loop ends, TM does not halt
        while(steps < maxSteps) {

            // Get applicable rules from current state
            Rule[] currentRules = getAvaliableRules(currentState);

            // Look for matching input read
            for (Rule rule : currentRules) {

                // Check for end of input blanks
                if (head >= tape.size()) {
                    tape.add('B');
                }

                if (tape.get(head) == rule.getCharToRead()) {

                   // Move to next state
                   currentState = rule.getOutputState();
                   tape.remove(head);
                   tape.add(head, rule.getCharToWrite());
                   head += rule.getDirection();

                   // Make sure tape head doesn't go off left edge
                   if (head < 0)
                       head = 0;

                   steps++;
                   break;
               }
            }

            if (currentState == 1) {
                // Accept
                return 0;
            } else if (currentState == 2) {
                // Reject
                return 1;
            }
        }

        // Does not halt after maxSteps
        return -1;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numTM = scan.nextInt();
        TM[] tms = new TM[numTM];
        String[][] totalInputs = new String[numTM][];

        for (int i = 0; i < tms.length; i++) {
            int numStates = scan.nextInt();
            int numRules = scan.nextInt();

            Rule[] rules = new Rule[numRules];
            for (int j = 0; j < rules.length; j++) {

                int inputState = scan.nextInt();
                char readChar = scan.next().charAt(0);
                int outputState = scan.nextInt();
                char writeChar = scan.next().charAt(0);
                int direction;
                if (scan.next().charAt(0) == 'L') {
                   direction = -1;
                } else {
                   direction = 1;
                }

                rules[j] = new Rule(inputState, readChar, outputState, writeChar, direction);
            }

            int numInput = scan.nextInt();
            int maxSteps = scan.nextInt();

            tms[i] = new TM(rules, maxSteps);

            totalInputs[i] = new String[numInput];
            for (int j = 0; j < totalInputs[i].length; j++) {
                totalInputs[i][j] = scan.next();
            }

        }

        for (int i = 0; i < totalInputs.length; i++) {
            System.out.println("Machine #" + (i + 1) + ":");

            for (int j = 0; j < totalInputs[i].length; j++) {
                int result = tms[i].run(totalInputs[i][j]);
                String msg;
                if (result == 0) {
                    msg = "YES";
                } else if (result == 1) {
                    msg = "NO";
                } else {
                    msg = "DOES NOT HALT IN " + tms[i].getMaxSteps() + " STEPS";
                }

                System.out.println(totalInputs[i][j] + ": " + msg);
            }
        }
    }
}
