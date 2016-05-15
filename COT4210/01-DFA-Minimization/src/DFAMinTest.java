import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 9/23/15.
 */
public class DFAMinTest {

    private DFA dfa;
    private DFA dfa2;
    private DFA dfa3;

    @Before
    public void setUp() throws Exception {
        int numStates = 8;
        int alphabetSize = 2;
        int[] aStates = new int[]{1, 2, 3, 4, 5, 6};
        int[][] transitions = new int[numStates][alphabetSize];
        transitions[0] = new int[]{1, 4};
        transitions[1] = new int[]{2, 3};
        transitions[2] = new int[]{7, 7};
        transitions[3] = new int[]{7, 3};
        transitions[4] = new int[]{5, 6};
        transitions[5] = new int[]{7, 7};
        transitions[6] = new int[]{7, 6};
        transitions[7] = new int[]{7, 7};
        dfa = new DFA(numStates, alphabetSize, aStates, transitions);


        numStates = 7;
        alphabetSize = 2;
        int[] aStates2 = new int[]{2, 3, 4};
        int[][] transitions2 = new int[numStates][alphabetSize];
        transitions2[0] = new int[]{1, 5};
        transitions2[1] = new int[]{3, 4};
        transitions2[2] = new int[]{0, 3};
        transitions2[3] = new int[]{6, 3};
        transitions2[4] = new int[]{6, 3};
        transitions2[5] = new int[]{2, 3};
        transitions2[6] = new int[]{5, 1};
        dfa2 = new DFA(numStates, alphabetSize, aStates2, transitions2);

        numStates = 6;
        alphabetSize = 2;
        int[] aStates3 = new int[]{0};
        int[][] transitions3 = new int[numStates][alphabetSize];
        transitions3[0] = new int[]{0, 1};
        transitions3[1] = new int[]{2, 3};
        transitions3[2] = new int[]{4, 5};
        transitions3[3] = new int[]{0, 1};
        transitions3[4] = new int[]{2, 3};
        transitions3[5] = new int[]{4, 5};
        dfa3 = new DFA(numStates, alphabetSize, aStates3, transitions3);
    }

    @Test
    public void testMinimizeDFA() throws Exception {
        DFA[] dfaArray = new DFA[3];
        dfaArray[0] = DFAMin.minimizeDFA(dfa);
        dfaArray[1] = DFAMin.minimizeDFA(dfa2);
        dfaArray[2] = DFAMin.minimizeDFA(dfa3);
        DFAMin.printDFAArray(dfaArray);
    }

    @Test
    public void testFindPairs() throws Exception {
        ArrayList<Set> acceptPairs = DFAMin.findPairs(dfa.getAStates());
        int[][] acceptPairsArray = new int[15][2];
        acceptPairsArray[0] = new int[]{1,2};
        acceptPairsArray[1] = new int[]{1,3};
        acceptPairsArray[2] = new int[]{1,4};
        acceptPairsArray[3] = new int[]{1,5};
        acceptPairsArray[4] = new int[]{1,6};
        acceptPairsArray[5] = new int[]{2,3};
        acceptPairsArray[6] = new int[]{2,4};
        acceptPairsArray[7] = new int[]{2,5};
        acceptPairsArray[8] = new int[]{2,6};
        acceptPairsArray[9] = new int[]{3,4};
        acceptPairsArray[10] = new int[]{3,5};
        acceptPairsArray[11] = new int[]{3,6};
        acceptPairsArray[12] = new int[]{4,5};
        acceptPairsArray[13] = new int[]{4,6};
        acceptPairsArray[14] = new int[]{5,6};
        int pairsIndex = 0;
        for (Set s : acceptPairs) {
            int i = 0;
            int[] intPair = new int[s.size()];
            for (Object o : s) {
                intPair[i] = (int)o;
                i++;
            }
            assertArrayEquals(acceptPairsArray[pairsIndex], intPair);
            pairsIndex++;
        }
        ArrayList<Set> rejectPairs = DFAMin.findPairs(dfa.getRStates());
        int[][] rejectPairsArray = new int[1][2];
        pairsIndex = 0;
        rejectPairsArray[0] = new int[]{0,7};
        for (Set s : rejectPairs) {
            int i = 0;
            int[] intPair = new int[s.size()];
            for (Object o : s) {
                intPair[i] = (int)o;
                i++;
            }
            assertArrayEquals(rejectPairsArray[pairsIndex], intPair);
            pairsIndex++;
        }
    }
}