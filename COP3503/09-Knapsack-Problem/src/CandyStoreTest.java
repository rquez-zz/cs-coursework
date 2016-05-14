import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ricardo on 7/26/2015.
 */
public class CandyStoreTest {

    @Test
    public void testSolve() throws Exception {

        assertEquals(796, CandyStore.solve(0, new int[]{700, 199}, new double[]{7.00, 2.00}, 8.00));
        assertEquals(798, CandyStore.solve(0, new int[]{700, 299, 499}, new double[]{7.00, 3.00, 5.00}, 8.00));
        assertEquals(595, CandyStore.solve(0, new int[]{500, 95}, new double[]{3.00, 1.50}, 5.00));
    }
}