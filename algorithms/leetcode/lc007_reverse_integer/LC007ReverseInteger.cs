// https://leetcode.com/problems/reverse-integer/

using System;

namespace AlgorithmDesign.leetcode.lc007_reverse_integer
{
    public class Solution
    {
        public int Reverse(int x)
        {
            if (x == Int32.MinValue)
            {
                return 0;
            }
            bool negative = x < 0;
            if (negative)
            {
                x = -x;
            }
            int[] digits = new int[10];
            int collected = 0;
            while (x > 0)
            {
                digits[collected++] = x % 10;
                x /= 10;
            }
            int[] limits = new int[] { 2, 1, 4, 7, 4, 8, 3, 6, 4, 7 };
            int length = limits.Length;
            int padding = length - collected;
            Array.Copy(digits, 0, digits, padding, collected);
            Array.Fill(digits, 0, 0, padding);
            for (int i = 0; i < length; i++)
            {
                if (digits[i] > limits[i])
                {
                    return 0;
                }
                if (digits[i] < limits[i])
                {
                    break;
                }
            }
            int result = 0;
            foreach (int digit in digits)
            {
                result = result * 10 + digit;
            }
            if (negative)
            {
                result = -result;
            }
            return result;
        }
    }
}
