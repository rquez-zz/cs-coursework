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
    public static void main(String[] args) {

    }
}
