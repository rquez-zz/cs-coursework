import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ricardo on 7/13/2015.
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

    }

    @Test
    public void testDecode() throws Exception {

    }

    @Test
    public void testLowers() throws Exception {

    }

    @Test
    public void testCount() throws Exception {

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