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

    }

    @Test
    public void testFreqs() throws Exception {

    }

    @Test
    public void testRotate() throws Exception {

    }

    @Test
    public void testChisqr() throws Exception {

    }

    @Test
    public void testPosition() throws Exception {

    }

    @Test
    public void testCrack() throws Exception {

    }
}