package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LC005LongestPalindromicSubstring {
    public class Solution {
        private boolean isPalindrome(String s, int start, int finish) {
            if (start < 0) return false;
            while (start < finish) {
                if (s.charAt(start++) != s.charAt(finish--)) {
                    return false;
                }
            }
            return true;
        }

        public String longestPalindromeBrute(String s) {
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

        private class TrieNode {
            public Map<Character, TrieNode> next = new HashMap<>();
        }

        private String extendTrie(String part, TrieNode node, String longest) {
            int length = part.length();
            for (int i = 0; i < length; i++) {
                char key = part.charAt(i);
                TrieNode deeper = node.next.get(key);
                if (null == deeper) {
                    deeper = new TrieNode();
                    node.next.put(key, deeper);
                }else{
                    String prefix = part.substring(0, i + 1);
                    if (longest.length() < prefix.length() && isPalindrome(prefix, 0, prefix.length() - 1)) {
                        longest = prefix;
                    }
                }
                node = deeper;
            }
            return longest;
        }

        public String longestPalindromeWithTrie(String s) {
            TrieNode root = new TrieNode();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                extendTrie(s.substring(i), root, "");
            }
            s = new StringBuilder(s).reverse().toString();
            String longest = "";
            for (int i = 0; i < length; i++) {
                longest = extendTrie(s.substring(i), root, longest);

            }
            return longest;
        }

        public String longestPalindrome(String s) {
            // Manacher algorithm
            int a = 0;
            int z = 0;
            int size = 0;
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (isPalindrome(s, i - size - 1, i)) {
                    a = i - size - 1;
                    z = i + 1;
                    size += 2;
                } else if (isPalindrome(s, i - size, i)) {
                    a = i - size;
                    z = i + 1;
                    size++;
                }
            }
            return s.substring(a, z);
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
