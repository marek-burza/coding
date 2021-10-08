package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * #hard
 */
public class LC060PermutationSequence {
    public final class Solution {
        public String getPermutation(int n, int k) {
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

    @Test
    public void test_2_1() throws Exception {
        assertEquals("12", new Solution().getPermutation(2, 1));
    }

    @Test
    public void test_3_2() throws Exception {
        assertEquals("132", new Solution().getPermutation(3, 2));
    }
}
