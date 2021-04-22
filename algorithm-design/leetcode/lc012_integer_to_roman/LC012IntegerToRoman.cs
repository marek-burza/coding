// https://leetcode.com/problems/integer-to-roman/
using System.Text;

namespace AlgorithmDesign.leetcode.lc012_integer_to_roman
{
    public class Solution
    {
        private readonly string[] _digits = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        private readonly int[] _weights = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        public string IntToRoman(int num)
        {
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < _digits.Length; i++)
            {
                int multiple = num / _weights[i];
                for (int j = 0; j < multiple; j++)
                {
                    roman.Append(_digits[i]);
                }
                num -= multiple * _weights[i];
            }
            return roman.ToString();
        }

    }
}
