// https://leetcode.com/problems/zigzag-conversion/

using System.Text;

namespace AlgorithmDesign.leetcode.lc006_zigzag_conversion
{
    public class Solution
    {
        public string Convert(string s, int numRows)
        {
            if (s == null || numRows < 1)
            {
                return null;
            }
            StringBuilder buffer = new StringBuilder();
            int n = s.Length;
            int scan = (numRows - 1) * 2;
            scan = scan == 0 ? 1 : scan;
            for (int row = 0; row < numRows; row++)
            {
                for (int i = row; i < n; i += scan)
                {
                    buffer.Append(s[i]);
                    if (0 < row && row < numRows - 1)
                    {
                        int offset = (numRows - 1 - row) * 2;
                        if (i + offset >= n)
                        {
                            break;
                        }
                        buffer.Append(s[i + offset]);
                    }
                }
            }
            return buffer.ToString();
        }
    }
}
