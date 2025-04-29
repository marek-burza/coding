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
```rust
pub fn find_power_loop(value: i32) -> Vec<i32> {
    let mut modulos = vec![];
    let mut lut = [false; 1337];
    let mut modulo = value;
    while !lut[modulo as usize] {
        lut[modulo as usize] = true;
        modulos.push(modulo);
        modulo = (modulo * value) % 1337;
    }
    modulos
}

pub fn modulo(dividend: &[i32], divisor: i32) -> i32 {
    let mut modulo = 0;
    for digit in dividend {
        modulo = ((modulo as i64 * 10 + (*digit) as i64) % (divisor as i64)) as i32;
    }
    modulo
}

pub fn super_pow(a: i32, b: Vec<i32>) -> i32 {
    let m = a % 1337;
    let modulos = find_power_loop(m);
    let length = modulos.len();
    let index = modulo(&b, length) as usize;
    (index - 1 + length) % length
        .checked_sub(1)
        .unwrap()
        .checked_add(length)
        .unwrap()
        .checked_mul(modulos[index])
        .unwrap()
}
```

Note that the original Java code used LinkedList and boolean arrays, while Rust's Vec and array types are more idiomatic. Additionally, Rust has a strong focus on safety and borrow checker, which would enforce the use of mutable references for certain variables. The above code uses i32 instead of int as they are equivalent in both languages.