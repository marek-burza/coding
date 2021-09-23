// https://leetcode.com/problems/generate-parentheses/

using System.Collections.Generic;

namespace AlgorithmDesign.code.leetcode.lc022_generate_parentheses
{
    public class Solution
    {
        private void GenerateParenthesis(string prefix, int standing, int n, IList<string> found)
        {
            if (n == 0 && standing == 0)
            {
                found.Add(prefix);
                return;
            }
            // open
            if (n > 0)
            {
                GenerateParenthesis(prefix + "(", standing + 1, n - 1, found);
            }
            // close
            if (standing > 0)
            {
                GenerateParenthesis(prefix + ")", standing - 1, n, found);
            }
        }

        public IList<string> GenerateParenthesis(int n)
        {
            List<string> found = new List<string>();
            if (n != 0)
            {
                GenerateParenthesis("(", 1, n - 1, found);
            }
            return found;
        }
    }
}
