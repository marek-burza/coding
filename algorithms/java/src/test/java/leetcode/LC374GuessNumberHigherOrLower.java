package leetcode.lc374_guess_number_higher_or_lower;

import org.junit.jupiter.api.Test;


import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 * #easy
 */
public final class LC374GuessNumberHigherOrLower {
    public final class GuessGame {
        public int x;

        protected int guess(final int n) {
            if (x < n) return -1;
            if (x == n) return 0;
            if (x > n) return 1;
            throw new RuntimeException();
        }
    }

    public final class Solution extends GuessGame {
        public int guessNumber(final int n) {
            int a = 1, z = n;
            while (a != z) {
                int checking = (int) (((long) a + (long) z) / 2);
                switch (guess(checking)) {
                    case 1:
                        a = checking + 1;
                        break;
                    case 0:
                        return checking;
                    case -1:
                        z = checking - 1;
                        break;
                }
            }
            return a;
        }
    }

    @Test
    public void test_random() throws Exception {
        Random random = new Random();
        Solution solution = new Solution();
        solution.x = random.nextInt(Integer.MAX_VALUE) + 1;
        assertEquals(solution.x, solution.guessNumber(Integer.MAX_VALUE));
    }
}
