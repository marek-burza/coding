// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

using System;
using System.Collections.Generic;

namespace AlgorithmDesign.leetcode.lc017_letter_combinations_of_a_phone_number
{
    public class Solution
    {
        public IList<string> LetterCombinations(string digits)
        {
            if (String.IsNullOrEmpty(digits)) return new List<string>();
            string[] mapping = { " ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
            IList<string> mapped = new List<string>();
            mapped.Add("");
            foreach (char digit in digits)
            {
                int index = digit - '0';
                List<string> longer = new List<string>();
                foreach (string prefix in mapped)
                {
                    foreach (char suffix in mapping[index])
                    {
                        longer.Add(prefix + suffix);
                    }
                }
                mapped = longer;
            }
            return mapped;
        }
    }
}
