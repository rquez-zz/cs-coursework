import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Ricardo Vasquez
 * CasearCipherTest.java
 */
public class CaesarCipherTest {

    @Test
    public void testLet2nat() throws Exception {
        assertEquals(1, CaesarCipher.let2nat('a'));
        assertEquals(10, CaesarCipher.let2nat('j'));
        assertEquals(20, CaesarCipher.let2nat('t'));
    }

    @Test
    public void testNat2let() throws Exception {
        assertEquals('a', CaesarCipher.nat2let(1));
        assertEquals('j', CaesarCipher.nat2let(10));
        assertEquals('t', CaesarCipher.nat2let(20));
    }

    @Test
    public void testShift() throws Exception {
        assertEquals('d', CaesarCipher.shift(3,'a'));
        assertEquals('k', CaesarCipher.shift(10,'a'));
        assertEquals('u', CaesarCipher.shift(20,'a'));
        assertEquals('A', CaesarCipher.shift(20,'A'));
    }

    @Test
    public void testEncode() throws Exception {
        assertEquals("kdvnhoolvixq", CaesarCipher.encode(3, "haskellisfun"));
        assertEquals("pbqdphlvulfdugr", CaesarCipher.encode(3, "mynameisricardo"));
        assertEquals("ykzmyqueduomdpa", CaesarCipher.encode(12, "mynameisricardo"));
    }

    @Test
    public void testDecode() throws Exception {
        assertEquals("haskellisfun", CaesarCipher.decode(3, "kdvnhoolvixq"));
        assertEquals("mynameisricardo", CaesarCipher.decode(3, "pbqdphlvulfdugr"));
        assertEquals("mynameisricardo", CaesarCipher.decode(12, "ykzmyqueduomdpa"));
    }

    @Test
    public void testLowers() throws Exception {
        assertEquals(12, CaesarCipher.lowers("haskellisfun"));
        assertEquals(2, CaesarCipher.lowers("ADFADFAaaDFADFAD"));
        assertEquals(0, CaesarCipher.lowers(""));
    }

    @Test
    public void testCount() throws Exception {
        assertEquals(2, CaesarCipher.count('s', "haskellisfun"));
        assertEquals(0, CaesarCipher.count('x', "haskellisfun"));
        assertEquals(0, CaesarCipher.count('s', ""));
    }

    @Test
    public void testPercent() throws Exception {
        assertEquals(16.6667, CaesarCipher.percent(2,12), .0001);
        assertEquals(11.9048, CaesarCipher.percent(5,42), .0001);
        assertEquals(500.0000, CaesarCipher.percent(10,2), .0001);
    }

    @Test
    public void testFreqs() throws Exception {
        assertArrayEquals(new double[]{8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0}, CaesarCipher.freqs("haskellisfun"), .0001);
    }

    @Test
    public void testRotate() throws Exception {
        assertArrayEquals(new double[]{3.0, 4.0, 1.0, 2.0}, CaesarCipher.rotate(2, new double[]{1.0, 2.0, 3.0, 4.0}), .01);
        assertArrayEquals(new double[]{2.0, 3.0, 4.0, 1.0}, CaesarCipher.rotate(1, new double[]{1.0, 2.0, 3.0, 4.0}), .01);
        assertArrayEquals(new double[]{0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333}, CaesarCipher.rotate(1, CaesarCipher.freqs("haskellisfun")), .0001);
    }

    @Test
    public void testChisqr() throws Exception {
        assertEquals(202.616, CaesarCipher.chisqr(CaesarCipher.freqs("haskellisfun")), .000000001);
    }

    @Test
    public void testPosition() throws Exception {
        assertEquals(2, CaesarCipher.position(5.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
        assertEquals(3, CaesarCipher.position(7.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
        assertEquals(1, CaesarCipher.position(1.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
    }

    @Test
    public void testCrack() throws Exception {

    }
}