package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LC017LetterCombinationsOfAPhoneNumber {
    public class Solution {
        public List<String> letterCombinations(String digits) {
            if (null == digits || digits.length() == 0) return new ArrayList<>();
            String[] mapping = {" ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
            List<String> mapped = new ArrayList<>();
            mapped.add("");
            for (char digit : digits.toCharArray()) {
                int index = digit - '0';
                List<String> longer = new ArrayList<>();
                for (Iterator<String> previous = mapped.iterator(); previous.hasNext(); ) {
                    String prefix = previous.next();
                    for (char suffix : mapping[index].toCharArray()) {
                        longer.add(prefix + suffix);
                    }
                }
                mapped = longer;
            }
            return mapped;
        }
    }


    @Test
    public void test_empty() throws Exception {
        List<String> result = new Solution().letterCombinations("");
        assertNotEquals(null, result);
        assertEquals(0, result.size());
    }

    @Test
    public void test_example() throws Exception {
        String[] expected = {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        Arrays.sort(expected);
        List<String> result = new Solution().letterCombinations("23");
        Collections.sort(result);
        assertNotEquals(null, result);
        assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i));
        }
    }
}
