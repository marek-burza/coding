package leetcode.lc005_longest_palindromic_substring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class LC005LongestPalindromicSubstringTests {
    @Test
    public void test1() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        assertEquals("bab", solution.longestPalindrome("babad"));
    }

    @Test
    public void test2() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        assertEquals("bb", solution.longestPalindrome("cbbd"));
    }

    @Test
    public void test3() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        assertEquals("a", solution.longestPalindrome("a"));
    }

    @Test
    public void test4() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        assertEquals("a", solution.longestPalindrome("ac"));
    }

    @Test
    public void testBb() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        assertEquals("bb", solution.longestPalindrome("bb"));
    }

    @Test
    public void testLonger() throws Exception {
        LC005LongestPalindromicSubstring solution;
        solution = new LC005LongestPalindromicSubstring();
        String longer = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        assertEquals("ranynar", solution.longestPalindrome(longer));
    }
}
