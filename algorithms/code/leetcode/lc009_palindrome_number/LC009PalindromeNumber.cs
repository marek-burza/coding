// https://leetcode.com/problems/palindrome-number/

namespace AlgorithmDesign.code.leetcode.lc009_palindrome_number
{
    public class Solution
    {
        public bool IsPalindrome(int x)
        {
            if (x < 0)
            {
                return false;
            }
            int xa = x;
            int xb = 0;
            while (x > 0)
            {
                xb = xb * 10 + x % 10;
                x /= 10;
            }
            return xa == xb;
        }
    }
}
