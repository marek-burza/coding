// https://leetcode.com/problems/divide-two-integers/

using System;

namespace AlgorithmDesign.leetcode.lc029_divide_two_integers
{
    public class Solution
    {
        public int Divide(int shortDividend, int shortDivisor)
        {
            long dividend = shortDividend;
            long divisor = shortDivisor;
            if (divisor == 0)
            {
                return Int32.MaxValue;
            }
            if (dividend == 0)
            {
                return 0;
            }
            long sign = (dividend / Math.Abs(dividend)) * (divisor / Math.Abs(divisor));
            dividend = Math.Abs(dividend);
            divisor = Math.Abs(divisor);
            long counter = 0;
            while (dividend >= divisor)
            {
                long subtractor = divisor;
                long incrementor = 1;
                while (dividend - subtractor - subtractor >= 0)
                {
                    subtractor += subtractor;
                    incrementor += incrementor;
                }
                dividend -= subtractor;
                counter += incrementor;
            }
            if (sign * counter > Int32.MaxValue)
            {
                return Int32.MaxValue;
            }
            return (int)(sign * counter);
        }
    }
}
