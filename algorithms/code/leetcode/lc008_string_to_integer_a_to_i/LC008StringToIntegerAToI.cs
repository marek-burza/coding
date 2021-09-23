// https://leetcode.com/problems/string-to-integer-atoi/
using System;

namespace AlgorithmDesign.code.leetcode.lc008_string_to_integer_a_to_i
{
    public class Solution
    {
        public int MyAtoi(string s)
        {
            long result = 0;
            long sign = 0;
            int index = 0;
            while (index < s.Length)
            {
                if (!Char.IsWhiteSpace(s[index]))
                {
                    break;
                }
                index++;
            }
            if (index < s.Length)
            {
                sign = s[index] == '-' ? -1 : 1;
                if (s[index] == '+' || s[index] == '-')
                {
                    index++;
                }
            }
            while (index < s.Length && Char.IsDigit(s[index]))
            {
                result *= 10;
                result += s[index] - '0';
                if (result > Int32.MaxValue)
                {
                    break;
                }
                index++;
            }
            result *= sign;
            return (int)Math.Max(Int32.MinValue, Math.Min(Int32.MaxValue, result));
        }
    }
}
