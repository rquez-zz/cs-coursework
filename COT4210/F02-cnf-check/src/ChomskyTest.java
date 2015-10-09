import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ricardo on 10/8/15.
 */
public class ChomskyTest {
    @Before
    public void setup() {

    }

    @Test
    public void testIsGrammar() {
        String[][] rules = new String[3][];
        rules[0] = new String[3];
        rules[0][0] = "S";
        rules[0][1] = "AB";
        rules[0][2] = "BB";
        rules[1] = new String[4];
        rules[1][0] = "A";
        rules[1][1] = "BB";
        rules[1][2] = "a";
        rules[1][3] = "b";
        rules[2] = new String[3];
        rules[2][0] = "B";
        rules[2][1] = "b";
        rules[2][2] = "c";

        Chomsky cnf = new Chomsky(rules);

        assertEquals(true, cnf.isInGrammar("bcb"));
    }
}
