package leetcode.lc060_permutation_sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/ #hard
 */
public final class LC060PermutationSequence {
    public String getPermutation(final int n, final int kValue) {
        int k = kValue;
        if (n < 0 || k < 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        List<Integer> remaining = new ArrayList<>();
        List<Integer> factorials = new ArrayList<>();
        factorials.add(0);
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorials.add(factorial);
            remaining.add(i);
        }
        for (int i = 1; i < n; i++) {
            int block = factorials.get(n - i);
            int index = (k - 1) / block;
            result.append(remaining.remove(index));
            k -= index * block;
        }
        result.append(remaining.remove(0));
        return result.toString();
    }
}
package leetcode.lc060_permutation_sequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC060PermutationSequenceTests {
    @Test
    public void test2And1() throws Exception {
        assertEquals("12", new LC060PermutationSequence().getPermutation(2, 1));
    }

    @Test
    public void test3And2() throws Exception {
        assertEquals("132", new LC060PermutationSequence().getPermutation(3, 2));
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC060PermutationSequence().getPermutation(1, -1));
        assertNull(new LC060PermutationSequence().getPermutation(-1, 1));
    }
}
