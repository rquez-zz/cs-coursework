import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TMTest {

    TM tm1;
    TM tm2;
    TM tm3;
    TM tm4;

    @Before
    public void setup() {

        Rule[] rules1 = new Rule[9];
        rules1[0] = new Rule(0, 'B', 2, 'B', 1);
        rules1[1] = new Rule(0, 'a', 3, 'a', 1);
        rules1[2] = new Rule(0, 'b', 4, 'b', 1);
        rules1[3] = new Rule(3, 'a', 1, 'a', 1);
        rules1[4] = new Rule(3, 'b', 1, 'b', 1);
        rules1[5] = new Rule(3, 'B', 1, 'B', 1);
        rules1[6] = new Rule(4, 'a', 2, 'a', 1);
        rules1[7] = new Rule(4, 'b', 2, 'b', 1);
        rules1[8] = new Rule(4, 'B', 2, 'B', 1);
        tm1 = new TM(rules1, 10);

    }

    @Test
    public void runTest() {
        assertEquals(0, tm1.run("aaab"));
        assertEquals(1, tm1.run("ba"));
        assertEquals(1, tm1.run("b"));
    }
}
