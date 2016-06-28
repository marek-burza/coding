package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LC005LongestPalindromicSubstring {
    public class Solution {
        private boolean isPalindrome(String s, int start, int finish) {
            for (int i = start; i <= finish; i++) {
                if (s.charAt(i) != s.charAt(finish - (i - start))) {
                    return false;
                }
            }
            return true;
        }

        public String longestPalindrome(String s) {
            StringBuilder longest = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + longest.length(); j < s.length(); j++) {
                    if (isPalindrome(s, i, j)) {
                        longest.setLength(0);
                        longest.append(s.substring(i, j + 1));
                    }
                }
            }
            return longest.toString();
        }
    }

    @Test
    public void test_dvdb() throws Exception {
        assertEquals("dvd", new Solution().longestPalindrome("dvdb"));
    }

    @Test
    public void test_bb() throws Exception {
        assertEquals("bb", new Solution().longestPalindrome("bb"));
    }

    @Test
    public void test_a() throws Exception {
        assertEquals("a", new Solution().longestPalindrome("a"));
    }

    @Test
    public void test_longer() throws Exception {
        String longer = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        assertEquals("ranynar", new Solution().longestPalindrome(longer));
    }
}
