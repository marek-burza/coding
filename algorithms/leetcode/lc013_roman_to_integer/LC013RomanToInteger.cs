// https://leetcode.com/problems/roman-to-integer/
using System.Collections.Generic;

namespace AlgorithmDesign.leetcode.lc013_roman_to_integer
{
    public class Solution
    {
        private readonly Dictionary<char, int> _lut = new()
        {
            { 'I', 1 },
            { 'V', 5 },
            { 'X', 10 },
            { 'L', 50 },
            { 'C', 100 },
            { 'D', 500 },
            { 'M', 1000 }
        };

        public int RomanToInt(string s)
        {
            {
                if (s == null) return 0;
                int result = 0;
                int previous = 0;
                for (int i = s.Length - 1; i >= 0; i--)
                {
                    int current = _lut[s[i]];
                    current = current < previous ? -current : current;
                    result += current;
                    previous = current;
                }
                return result;
            }
        }
    }
}
