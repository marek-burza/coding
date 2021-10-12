package leetcode;

import org.junit.jupiter.api.Test;


import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/super-pow/
 * #medium
 */
public class LC372SuperPow {
    public class Solution {
        private static final int MODULO_1337 = 1337;

        private List<Integer> findPowerLoop(int value) {
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

        private int modulo(int[] dividend, int divisor) {
            int length = dividend.length;
            int modulo = 0;
            for (int i = 0; i < length; i++) {
                modulo = (modulo * 10 + dividend[i]) % divisor;
            }
            return modulo;
        }

        public int superPow(int a, int[] b) {
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

    @Test
    public void test_2_3() throws Exception {
        assertEquals(8, new Solution().superPow(2, new int[]{3}));
    }

    @Test
    public void test_2_1_0() throws Exception {
        assertEquals(1024, new Solution().superPow(2, new int[]{1, 0}));
    }
}
