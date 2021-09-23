// https://leetcode.com/problems/longest-palindromic-substring/
// See also: Manacher algorithm

namespace AlgorithmDesign.code.leetcode.lc005_longest_palindromic_substring
{
    public class Solution
    {
        public string LongestPalindrome(string s)
        {
            string longest = "";
            int index = 0;
            while (index < s.Length)
            {
                // Find head & tail
                int head = index, tail = index;
                while (tail + 1 < s.Length)
                {
                    if (s[head] == s[tail + 1])
                    {
                        tail++;
                    }
                    else
                    {
                        break;
                    }
                }
                index += 1 + tail - head;
                // Expand
                while (0 <= head - 1 && tail + 1 < s.Length && s[head - 1] == s[tail + 1])
                {
                    head--;
                    tail++;
                }
                // Check
                if (longest.Length < 1 + tail - head)
                {
                    longest = s.Substring(head, 1 + tail - head);
                }
            }
            return longest;
        }
    }
}
