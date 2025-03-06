package leetcode.lc372_super_pow;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/super-pow/
 * #medium
 */
public final class LC372SuperPow {
    private static final int MODULO_1337 = 1337;

    private List<Integer> findPowerLoop(final int value) {
        List<Integer> modulos = new LinkedList<>();
        boolean[] lut = new boolean[MODULO_1337];
        int modulo = value;
        while (!lut[modulo]) {
            lut[modulo] = true;
            modulos.add(modulo);
            modulo = (modulo * value) % MODULO_1337;
        }
        return modulos;
    }

    private int modulo(final int[] dividend, final int divisor) {
        int length = dividend.length;
        int modulo = 0;
        for (int i = 0; i < length; i++) {
            modulo = (modulo * 10 + dividend[i]) % divisor;
        }
        return modulo;
    }

    public int superPow(final int a, final int[] b) {
        // Assume: a = (1337 * n + m) where 0 <= m < 1337
        // Then: a^b mod 1337 = (1337 * n + m)^n mod 1337 == m^b mod 1337
        // This multiplication will cycle through certain "digits" of base 1337
        // You can search for the loop by iterating
        int m = a % MODULO_1337;
        List<Integer> modulos = findPowerLoop(m);
        // Get rid of loops from the power
        int length = modulos.size();
        int index = modulo(b, length);
        // Look-up the power modulo
        index = (index - 1 + length) % length;
        return modulos.get(index);
    }
}
package leetcode.lc372_super_pow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC372SuperPowTests {
    @Test
    public void test23() throws Exception {
        assertEquals(8, new LC372SuperPow().superPow(2, new int[] { 3 }));
    }

    @Test
    public void test210() throws Exception {
        assertEquals(1024, new LC372SuperPow().superPow(2, new int[] { 1, 0 }));
    }
}
