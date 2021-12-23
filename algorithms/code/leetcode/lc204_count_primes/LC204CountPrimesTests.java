package leetcode.lc204_count_primes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC204CountPrimesTests {
    @Test
    public void test11() throws Exception {
        assertEquals(4, new LC204CountPrimes().countPrimes(11));
    }

    @Test
    public void test1() throws Exception {
        assertEquals(0, new LC204CountPrimes().countPrimes(1));
    }
}
