// https://leetcode.com/problems/longest-common-prefix/

namespace AlgorithmDesign.leetcode.lc014_longest_common_prefix
{
    public class Solution
    {
        public string LongestCommonPrefix(string[] strs)
        {
            if (strs == null || strs.Length == 0)
            {
                return "";
            }
            int i = 0;
            bool done = false;
            while (!done)
            {
                foreach (string str in strs)
                {
                    if (str == null)
                    {
                        return "";
                    }
                    if (i >= str.Length || str[i] != strs[0][i])
                    {
                        done = true;
                        break;
                    }
                }
                i++;
            }
            return strs[0].Substring(0, i - 1);
        }
    }
}
