import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ricardo on 9/23/15.
 */
public class DFATest {

    @Test
    public void testGetRejectStates() throws Exception {
        int[] aStates = {1, 2, 3, 4, 5, 6};
        int numStates = 8;
        assertArrayEquals(DFA.getRejectStates(aStates, numStates), new int[]{0, 7});
        aStates = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        assertArrayEquals(DFA.getRejectStates(aStates, numStates), new int[]{});
    }
}