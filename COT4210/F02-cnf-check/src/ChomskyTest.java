import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ricardo on 10/8/15.
 */
public class ChomskyTest {

    String[][] rules1;
    String[][] rules2;
    String[][] rules3;

    @Before
    public void setup() {

        rules1 = new String[4][];
        rules1[0] = new String[3];
        rules1[0][0] = "S";
        rules1[0][1] = "AB";
        rules1[0][2] = "BC";
        rules1[1] = new String[3];
        rules1[1][0] = "A";
        rules1[1][1] = "BA";
        rules1[1][2] = "a";
        rules1[2] = new String[3];
        rules1[2][0] = "B";
        rules1[2][1] = "CC";
        rules1[2][2] = "b";
        rules1[3] = new String[3];
        rules1[3][0] = "C";
        rules1[3][1] = "AB";
        rules1[3][2] = "a";

        rules2 = new String[3][];
        rules2[0] = new String[3];
        rules2[0][0] = "S";
        rules2[0][1] = "AB";
        rules2[0][2] = "BB";
        rules2[1] = new String[4];
        rules2[1][0] = "A";
        rules2[1][1] = "BB";
        rules2[1][2] = "a";
        rules2[1][3] = "b";
        rules2[2] = new String[3];
        rules2[2][0] = "B";
        rules2[2][1] = "b";
        rules2[2][2] = "c";

        rules3 = new String[2][];
        rules3[0] = new String[3];
        rules3[0][0] = "S";
        rules3[0][1] = "AA";
        rules3[0][2] = "@";
        rules3[1] = new String[4];
        rules3[1][0] = "A";
        rules3[1][1] = "AA";
        rules3[1][2] = "x";
        rules3[1][3] = "y";
    }

    @Test
    public void testIsInGrammar() {
        Chomsky cnf1 = new Chomsky(rules1);
        Chomsky cnf2 = new Chomsky(rules2);
        Chomsky cnf3 = new Chomsky(rules3);

        assertTrue(cnf1.isInGrammar("baaba"));
        assertTrue(cnf2.isInGrammar("bcb"));
        assertTrue(!cnf2.isInGrammar("c"));
        assertTrue(cnf3.isInGrammar("@"));
        assertTrue(!cnf3.isInGrammar("asd"));
        assertTrue(cnf3.isInGrammar("yyyy"));
    }

    @Test
    public void testBuildFirstDiagonal() {
        Chomsky cnf = new Chomsky(rules1);
        String[][] diagonal = new String[5][5];
        diagonal[0][0] = "B";
        diagonal[1][1] = "AC";
        diagonal[2][2] = "AC";
        diagonal[3][3] = "B";
        diagonal[4][4] = "AC";
        assertArrayEquals(diagonal, cnf.buildFirstDiagonal("baaba"));
    }

    @Test
    public void testIsInRules() {
        Chomsky cnf = new Chomsky(rules1);
        assertEquals("SA", cnf.getRulesForSymbols(new String[]{"BA", "BC"}));
    }

    @Test
    public void testBuildTriangleTable1() {
        Chomsky cnf = new Chomsky(rules1);

        String[][] table = cnf.buildTriangleTable("baaba");

        String[][] diagonalTest = new String[5][5];

        diagonalTest[0][0] = "B";
        diagonalTest[1][1] = "AC";
        diagonalTest[2][2] = "AC";
        diagonalTest[3][3] = "B";
        diagonalTest[4][4] = "AC";

        diagonalTest[1][0] = "SA";
        diagonalTest[2][1] = "B";
        diagonalTest[3][2] = "SC";
        diagonalTest[4][3] = "SA";

        diagonalTest[2][0] = "";
        diagonalTest[3][1] = "B";
        diagonalTest[4][2] = "B";

        diagonalTest[3][0] = "";
        diagonalTest[4][1] = "SAC";

        diagonalTest[4][0] = "SAC";

        assertArrayEquals(diagonalTest, table);
    }

    @Test
    public void testBuildTriangleTable2() {

        Chomsky cnf = new Chomsky(rules2);

        String[][] table = cnf.buildTriangleTable("bcb");

        String[][] diagonalTest = new String[3][3];

        diagonalTest[0][0] = "AB";
        diagonalTest[1][1] = "B";
        diagonalTest[2][2] = "AB";

        diagonalTest[1][0] = "SA";
        diagonalTest[2][1] = "SA";

        diagonalTest[2][0] = "S";

        assertArrayEquals(diagonalTest, table);

        String[][] table1 = cnf.buildTriangleTable("c");

        diagonalTest = new String[1][1];
        diagonalTest[0][0] = "B";

        assertArrayEquals(diagonalTest, table1);
    }
}
