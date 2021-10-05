package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * #easy
 */
public class LC387FirstUniqueCharacterInAString {
    public class Solution {
        public int firstUniqChar(String s) {
            int size = 'z' - 'a' + 1;
            int[] count = new int[size];
            int[] index = new int[size];
            int length = s.length();
            for (int i = length - 1; 0 <= i; i--) {
                int key = s.charAt(i) - 'a';
                index[key] = i;
                count[key]++;
            }
            int min = -1;
            for (int i = 0; i < size; i++) {
                if (count[i] == 1) {
                    if (min == -1 || index[i] < min) {
                        min = index[i];
                    }
                }
            }
            return min;
        }
    }

    @Test
    public void test_leetcode() throws Exception {
        assertEquals(0, new Solution().firstUniqChar("leetcode"));
    }

    @Test
    public void test_loveleetcode() throws Exception {
        assertEquals(2, new Solution().firstUniqChar("loveleetcode"));
    }

    @Test
    public void test_empty() throws Exception {
        assertEquals(-1, new Solution().firstUniqChar(""));
    }
}
