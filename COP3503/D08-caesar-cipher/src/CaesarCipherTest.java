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
        assertEquals("mfxpjqqnxkzs", CaesarCipher.encode(5, "haskellisfun"));
        assertEquals("rkcuovvscpex", CaesarCipher.encode(10, "haskellisfun"));
        assertEquals("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!", CaesarCipher.encode(10, "congratulations on completing the exam!"));
    }

    @Test
    public void testDecode() throws Exception {
        assertEquals("haskellisfun", CaesarCipher.decode(3, "kdvnhoolvixq"));
        assertEquals("mynameisricardo", CaesarCipher.decode(3, "pbqdphlvulfdugr"));
        assertEquals("mynameisricardo", CaesarCipher.decode(12, "ykzmyqueduomdpa"));
        assertEquals("haskellisfun", CaesarCipher.decode(5, "mfxpjqqnxkzs"));
        assertEquals("haskellisfun", CaesarCipher.decode(10, "rkcuovvscpex"));
        assertEquals("congratulations on completing the exam!", CaesarCipher.decode(10, "myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
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
        assertArrayEquals(new double[]{0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0}, CaesarCipher.freqs("rkcuovvscpex"), .0001);
    }

    @Test
    public void testRotate() throws Exception {
        assertArrayEquals(new double[]{3.0, 4.0, 1.0, 2.0}, CaesarCipher.rotate(2, new double[]{1.0, 2.0, 3.0, 4.0}), .01);
        assertArrayEquals(new double[]{2.0, 3.0, 4.0, 1.0}, CaesarCipher.rotate(1, new double[]{1.0, 2.0, 3.0, 4.0}), .01);
        assertArrayEquals(new double[]{2.0, 3.0, 4.0, 5.0, 6.0, 1.0}, CaesarCipher.rotate(1, new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0}), .01);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0, 1.0, 2.0, 3.0}, CaesarCipher.rotate(3, new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0}), .01);
        assertArrayEquals(new double[]{5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 1.0, 2.0, 3.0, 4.0}, CaesarCipher.rotate(4, new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0}), .01);
        assertArrayEquals(new double[]{8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0}, CaesarCipher.rotate(0, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333}, CaesarCipher.rotate(1, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0}, CaesarCipher.rotate(2, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0}, CaesarCipher.rotate(3, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0,0.0}, CaesarCipher.rotate(4, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0}, CaesarCipher.rotate(10, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0}, CaesarCipher.rotate(16, CaesarCipher.freqs("haskellisfun")), .0001);
        assertArrayEquals(new double[]{0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0,8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0}, CaesarCipher.rotate(0, CaesarCipher.freqs("rkcuovvscpex")), .0001);
        assertArrayEquals(CaesarCipher.freqs("rkcuovvscpex"), CaesarCipher.rotate(0, CaesarCipher.freqs("rkcuovvscpex")), .0001);
    }

    @Test
    public void testChisqr() throws Exception {
        assertEquals(202.616, CaesarCipher.chisqr(CaesarCipher.freqs("haskellisfun")), .001);
        assertEquals(202.616, CaesarCipher.chisqr(new double[]{8.33333,0.0,0.0,0.0,8.33333,8.33333,0.0,8.33333, 8.33333,0.0,8.33333,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,16.6667,0.0,8.33333,0.0,0.0,0.0,0.0,0.0}), .001);

    }

    @Test
    public void testPosition() throws Exception {
        assertEquals(2, CaesarCipher.position(5.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
        assertEquals(3, CaesarCipher.position(7.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
        assertEquals(0, CaesarCipher.position(1.0, new double[]{1.0, 3.0, 5.0, 7.0, 11.0}));
    }

    @Test
    public void testCrack() throws Exception {
        assertEquals("haskellisfun", CaesarCipher.crack(CaesarCipher.encode(3, "haskellisfun")));
        assertEquals("haskellisfun", CaesarCipher.crack(CaesarCipher.encode(5, "haskellisfun")));
        assertEquals("haskellisfun", CaesarCipher.crack(CaesarCipher.encode(10, "haskellisfun")));
        assertEquals("haskellisfun", CaesarCipher.crack(CaesarCipher.encode(15, "haskellisfun")));
        assertEquals("nyhoht", CaesarCipher.crack(CaesarCipher.encode(3, "graham")));
        assertEquals("somethingiscommon", CaesarCipher.crack(CaesarCipher.encode(3, "somethingiscommon")));
        assertEquals("congratulations on completing the exam!", CaesarCipher.crack("myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!"));
        assertEquals("congratulations on completing the exam!", CaesarCipher.crack(CaesarCipher.encode(10, "congratulations on completing the exam!")));
    }
}