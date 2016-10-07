package codility;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * http://blog.codility.com/2011/03/solutions-for-task-equi.html
 */
public class Equi {
    public class Solution {
        public int solution(int[] A) {
            int n = A.length;
            if (n == 0) return -1;
            BigInteger[] after = new BigInteger[n];
            after[n - 1] = BigInteger.ZERO;
            for (int i = 1; i < n; i++) {
                after[n - 1 - i] = after[n - i].add(BigInteger.valueOf(A[n - i]));
            }
            BigInteger sum = BigInteger.ZERO;
            for (int i = 0; i < n; i++) {
                if (sum.equals(after[i])) {
                    return i;
                }
                sum = sum.add(BigInteger.valueOf(A[i]));
            }
            return -1;
        }
    }

    @Test
    public void test() {
        assertEquals(1, new Solution().solution(new int[]{-1, 3, -4, 5, 1, -6, 2, 1}));
    }
}
