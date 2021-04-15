using Xunit;

namespace AlgorithmDesign.leetcode.lc001_two_sum
{
    public class Lc001TwoSumTests
    {
        [Fact]
        public void Test0()
        {
            Assert.Equal(null, new Lc001TwoSum().TwoSum(null, 0));
        }

        [Fact]
        public void Test00()
        {
            Assert.Equal(null, new Lc001TwoSum().TwoSum(new int[] { }, 0));
        }

        [Fact]
        public void Test1()
        {
            Assert.Equal(new[] { 0, 1 }, new Lc001TwoSum().TwoSum(new[] { 2, 7, 11, 15 }, 9));
        }

        [Fact]
        public void Test2()
        {
            Assert.Equal(new[] { 1, 2 }, new Lc001TwoSum().TwoSum(new[] { 3, 2, 4 }, 6));
        }

        [Fact]
        public void Test3()
        {
            Assert.Equal(new[] { 0, 1 }, new Lc001TwoSum().TwoSum(new[] { 3, 3 }, 6));
        }
    }
}
