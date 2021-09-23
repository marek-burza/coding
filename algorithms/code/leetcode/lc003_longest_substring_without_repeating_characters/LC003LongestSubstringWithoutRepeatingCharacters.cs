// https://leetcode.com/problems/longest-substring-without-repeating-characters/
using System;
using System.Collections.Generic;

namespace AlgorithmDesign.code.leetcode.lc003_longest_substring_without_repeating_characters
{
    public class Solution
    {
        public int LengthOfLongestSubstring(string s)
        {
            ICollection<char> seen = new HashSet<char>();
            int longest = 0;
            int count = 0;
            for (int i = 0; i < s.Length; i++)
            {
                char found = s[i];
                while (count > 0 && seen.Contains(found))
                {
                    seen.Remove(s[i - count]);
                    count--;
                }
                count++;
                seen.Add(found);
                longest = Math.Max(longest, count);
            }
            return longest;
        }
    }
}
