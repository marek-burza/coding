package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/count-and-say/
 * #medium
 */
public class LC038CountAndSay {
    public final class Solution {
        public String countAndSay(int n) {
            if (n < 1) return null;
            String result = "1";
            while (n > 1) {
                StringBuilder current = new StringBuilder();
                char check = '0';
                int count = 0;
                for (char character : result.toCharArray()) {
                    if (check != character) {
                        if (count > 0) {
                            current.append(count);
                            current.append(check);
                        }
                        count = 1;
                        check = character;
                    } else {
                        count++;
                    }
                }
                if (count > 0) {
                    current.append(count);
                    current.append(check);
                }
                n--;
                result = current.toString();
            }
            return result;
        }
    }

    @Test
    public void test_1() throws Exception {
        assertEquals("1", new Solution().countAndSay(1));
    }

    @Test
    public void test_2() throws Exception {
        assertEquals("11", new Solution().countAndSay(2));
    }

    @Test
    public void test_3() throws Exception {
        assertEquals("21", new Solution().countAndSay(3));
    }

    @Test
    public void test_4() throws Exception {
        assertEquals("1211", new Solution().countAndSay(4));
    }

    @Test
    public void test_5() throws Exception {
        assertEquals("111221", new Solution().countAndSay(5));
    }
}
