using System.Linq;
using Xunit;

namespace AlgorithmDesign.code.leetcode.lc027_remove_element
{
    public class SolutionTests
    {
        [Fact]
        public void test_0_42_1_2_42_3_4__42()
        {
            int[] nums = { 0, 42, 1, 2, 42, 3, 4 };
            int length = new Solution().RemoveElement(nums, 42);
            Assert.Equal(5, length);
            int[] expected = { 0, 1, 2, 3, 4 };
            Assert.Equal(expected, nums.Take(length));
        }

        [Fact]
        public void test_nothing()
        {
            int length = new Solution().RemoveElement(null, 42);
            Assert.Equal(0, length);
        }
    }
}
