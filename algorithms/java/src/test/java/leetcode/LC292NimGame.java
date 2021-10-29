package leetcode.lc292_nim_game;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/nim-game/
 * #easy
 */
public final class LC292NimGame {
    public final class Solution {
        public boolean canWinNim(final int n) {
            return n % 4 != 0;
        }
    }

    @Test
    public void test_1to10() throws Exception {
        assertTrue(new Solution().canWinNim(1));  // +  1 o
        assertTrue(new Solution().canWinNim(2));  // +  2 o o
        assertTrue(new Solution().canWinNim(3));  // +  3 o o o
        assertFalse(new Solution().canWinNim(4)); // -  4 o ? ? x
        assertTrue(new Solution().canWinNim(5));  // +  5 o x ? ? o
        assertTrue(new Solution().canWinNim(6));  // +  6 o o x ? ? o
        assertTrue(new Solution().canWinNim(7));  // +  7 o o o x ? ? o
        assertFalse(new Solution().canWinNim(8)); // -  8 o ? ? . . . . x     (leads to 7, 6 or 5)
        assertTrue(new Solution().canWinNim(9));  // +  9 o x ? ? . . . . o   (leads to 8)
        assertTrue(new Solution().canWinNim(10)); // + 10 o o x ? ? . . . . o (leads to 8)
    }
}
