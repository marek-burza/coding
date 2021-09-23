using System.Linq;
using Xunit;

namespace AlgorithmDesign.code.leetcode.lc026_remove_duplicates_from_sorted_array
{
    public class SolutionTests
    {
        [Fact]
        public void test_1_2_2_3_4_4_7()
        {
            int[] nums1 = { 1, 2, 2, 3, 4, 4, 7 };
            int length = new Solution().RemoveDuplicates(nums1);
            Assert.Equal(5, length);
            int[] expected = { 1, 2, 3, 4, 7 };
            Assert.Equal(expected, nums1.Take(length));
        }
    }
}
