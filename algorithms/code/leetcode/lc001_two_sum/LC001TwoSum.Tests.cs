using Xunit;

namespace AlgorithmDesign.code.leetcode.lc001_two_sum
{
    public class SolutionTests
    {
        [Fact]
        public void Test0()
        {
            Assert.Null(new Solution().TwoSum(null, 0));
        }

        [Fact]
        public void Test00()
        {
            Assert.Null(new Solution().TwoSum(new int[] { }, 0));
        }

        [Fact]
        public void Test1()
        {
            Assert.Equal(new[] { 0, 1 }, new Solution().TwoSum(new[] { 2, 7, 11, 15 }, 9));
        }

        [Fact]
        public void Test2()
        {
            Assert.Equal(new[] { 1, 2 }, new Solution().TwoSum(new[] { 3, 2, 4 }, 6));
        }

        [Fact]
        public void Test3()
        {
            Assert.Equal(new[] { 0, 1 }, new Solution().TwoSum(new[] { 3, 3 }, 6));
        }
    }
}
