import java.lang.reflect.Array;
import java.util.ArrayList;

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


    public static void main(String[] args) {

    }
}
