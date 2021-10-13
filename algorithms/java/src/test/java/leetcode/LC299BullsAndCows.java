package leetcode.lc299_bulls_and_cows;

import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/bulls-and-cows/
 * #medium
 */
public final class LC299BullsAndCows {
    public final class Solution {
        public String getHint(final String secret, final String guess) {
            HashMap<Character, Integer> countKnown = new HashMap<>();
            HashMap<Character, Integer> countAsked = new HashMap<>();
            int bulls = 0, cows = 0, count;
            for (int i = 0; i < secret.length() && i < guess.length(); i++) {
                // Count characters of each type
                char known = secret.charAt(i);
                char asked = guess.charAt(i);
                count = 0;
                if (countKnown.containsKey(known)) {
                    count = countKnown.get(known);
                }
                countKnown.put(known, count + 1);
                count = 0;
                if (countAsked.containsKey(asked)) {
                    count = countAsked.get(asked);
                }
                countAsked.put(asked, count + 1);
                // Check for a bull
                if (known == asked) {
                    bulls++;
                }
            }
            // Count the cows
            for (char asked : countAsked.keySet()) {
                if (countKnown.containsKey(asked)) {
                    cows += Math.min(countKnown.get(asked), countAsked.get(asked));
                }
            }
            // Remove the bulls from the cows
            cows -= bulls;
            return "" + bulls + "A" + cows + "B";
            // It would have been faster to have one lookup table and update cows up or down accordingly
        }
    }

    @Test
    public void test_1807__7810() throws Exception {
        assertEquals("1A3B", new Solution().getHint("1807", "7810"));
    }

    @Test
    public void test_1123__0111() throws Exception {
        assertEquals("1A1B", new Solution().getHint("1123", "0111"));
    }

    @Test
    public void test1122and2211() throws Exception {
        assertEquals("0A4B", new Solution().getHint("1122", "2211"));
    }

    @Test
    public void test11and10() throws Exception {
        assertEquals("1A0B", new Solution().getHint("11", "10"));
    }
}
