using Xunit;
using System.Collections.Generic;

namespace AlgorithmDesign.code.leetcode.lc015_three_sum
{
    public class SolutionTests
    {
        private void check(int[][] expected, IList<IList<int>> result)
        {
            Assert.Equal(expected.Length, result.Count);
            foreach (int[] entry in expected)
            {
                foreach (IList<int> candidate in result)
                {
                    bool matching = true;
                    for (int i = 0; i < 3; i++)
                    {
                        if (entry[i] != candidate[i])
                        {
                            matching = false;
                            break;
                        }
                    }
                    if (matching)
                    {
                        result.Remove(candidate);
                        break;
                    }
                }
            }
            Assert.Equal(0, result.Count);
        }

        [Fact]
        public void test_example()
        {
            int[] s = { -1, 0, 1, 2, -1, -4 };
            int[][] expected = { new[] { -1, 0, 1 }, new[] { -1, -1, 2 } };
            check(expected, new Solution().ThreeSum(s));
        }

        [Fact]
        public void test_oversized()
        {
            int[] s = new[] { 14, -11, -2, -3, 4, -3, -3, -8, -15, 11, 11, -6, -14, -13, 5, -10, -13, 0, -12, -8, 14, -12, -10, 2, -4, 9, 13, 10, 2, 7, -2, -7, 4, 11, 5, -7, -15, 10, -7, -14, 6, 11, -5, 7, 6, 8, 5, 8, -7, 8, -15, 14, 11, 13, 1, -15, 7, 0, 10, -14, 14, -15, -14, 3, 4, 6, 4, 4, -7, 12, 5, 14, 0, 8, 7, 13, 1, -11, -2, 9, 12, -1, 8, 0, -11, -5, 0, 11, 2, -13, 4, 1, -12, 5, -10, -1, -12, 9, -12, -11, -2, 9, -12, 5, -6, 2, 4, 10, 6, -13, 4, 3, -7, -11, 11, -3, -9, -4, -15, 8, -9, -4, -13, -14, 8, 14 };
            int[][] expected = { new[] { -15, 1, 14 }, new[] { -15, 2, 13 }, new[] { -15, 3, 12 }, new[] { -15, 4, 11 }, new[] { -15, 5, 10 }, new[] { -15, 6, 9 }, new[] { -15, 7, 8 }, new[] { -14, 0, 14 }, new[] { -14, 1, 13 }, new[] { -14, 2, 12 }, new[] { -14, 3, 11 }, new[] { -14, 4, 10 }, new[] { -14, 5, 9 }, new[] { -14, 6, 8 }, new[] { -14, 7, 7 }, new[] { -13, -1, 14 }, new[] { -13, 0, 13 }, new[] { -13, 1, 12 }, new[] { -13, 2, 11 }, new[] { -13, 3, 10 }, new[] { -13, 4, 9 }, new[] { -13, 5, 8 }, new[] { -13, 6, 7 }, new[] { -12, -2, 14 }, new[] { -12, -1, 13 }, new[] { -12, 0, 12 }, new[] { -12, 1, 11 }, new[] { -12, 2, 10 }, new[] { -12, 3, 9 }, new[] { -12, 4, 8 }, new[] { -12, 5, 7 }, new[] { -12, 6, 6 }, new[] { -11, -3, 14 }, new[] { -11, -2, 13 }, new[] { -11, -1, 12 }, new[] { -11, 0, 11 }, new[] { -11, 1, 10 }, new[] { -11, 2, 9 }, new[] { -11, 3, 8 }, new[] { -11, 4, 7 }, new[] { -11, 5, 6 }, new[] { -10, -4, 14 }, new[] { -10, -3, 13 }, new[] { -10, -2, 12 }, new[] { -10, -1, 11 }, new[] { -10, 0, 10 }, new[] { -10, 1, 9 }, new[] { -10, 2, 8 }, new[] { -10, 3, 7 }, new[] { -10, 4, 6 }, new[] { -10, 5, 5 }, new[] { -9, -5, 14 }, new[] { -9, -4, 13 }, new[] { -9, -3, 12 }, new[] { -9, -2, 11 }, new[] { -9, -1, 10 }, new[] { -9, 0, 9 }, new[] { -9, 1, 8 }, new[] { -9, 2, 7 }, new[] { -9, 3, 6 }, new[] { -9, 4, 5 }, new[] { -8, -6, 14 }, new[] { -8, -5, 13 }, new[] { -8, -4, 12 }, new[] { -8, -3, 11 }, new[] { -8, -2, 10 }, new[] { -8, -1, 9 }, new[] { -8, 0, 8 }, new[] { -8, 1, 7 }, new[] { -8, 2, 6 }, new[] { -8, 3, 5 }, new[] { -8, 4, 4 }, new[] { -7, -7, 14 }, new[] { -7, -6, 13 }, new[] { -7, -5, 12 }, new[] { -7, -4, 11 }, new[] { -7, -3, 10 }, new[] { -7, -2, 9 }, new[] { -7, -1, 8 }, new[] { -7, 0, 7 }, new[] { -7, 1, 6 }, new[] { -7, 2, 5 }, new[] { -7, 3, 4 }, new[] { -6, -6, 12 }, new[] { -6, -5, 11 }, new[] { -6, -4, 10 }, new[] { -6, -3, 9 }, new[] { -6, -2, 8 }, new[] { -6, -1, 7 }, new[] { -6, 0, 6 }, new[] { -6, 1, 5 }, new[] { -6, 2, 4 }, new[] { -6, 3, 3 }, new[] { -5, -5, 10 }, new[] { -5, -4, 9 }, new[] { -5, -3, 8 }, new[] { -5, -2, 7 }, new[] { -5, -1, 6 }, new[] { -5, 0, 5 }, new[] { -5, 1, 4 }, new[] { -5, 2, 3 }, new[] { -4, -4, 8 }, new[] { -4, -3, 7 }, new[] { -4, -2, 6 }, new[] { -4, -1, 5 }, new[] { -4, 0, 4 }, new[] { -4, 1, 3 }, new[] { -4, 2, 2 }, new[] { -3, -3, 6 }, new[] { -3, -2, 5 }, new[] { -3, -1, 4 }, new[] { -3, 0, 3 }, new[] { -3, 1, 2 }, new[] { -2, -2, 4 }, new[] { -2, -1, 3 }, new[] { -2, 0, 2 }, new[] { -2, 1, 1 }, new[] { -1, -1, 2 }, new[] { -1, 0, 1 }, new[] { 0, 0, 0 } };
            check(expected, new Solution().ThreeSum(s));
        }

        [Fact]
        public void test_empty()
        {
            int[] s = new int[] { };
            Assert.Equal(0, new Solution().ThreeSum(s).Count);
        }
    }
}
