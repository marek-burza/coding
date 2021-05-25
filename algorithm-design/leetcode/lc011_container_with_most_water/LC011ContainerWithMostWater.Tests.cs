using Xunit;

namespace AlgorithmDesign.leetcode.lc011_container_with_most_water
{
    public class SolutionTests
    {
        [Fact]
        public void Test_1_2_1()
        {
            int[] test = { 1, 2, 1 };
            Assert.Equal(2, new Solution().MaxArea(test));
        }

        [Fact]
        public void Test_1_3_5_2()
        {
            int[] test = { 1, 3, 5, 2 };
            Assert.Equal(4, new Solution().MaxArea(test));
        }

        [Fact]
        public void TestOversized()
        {
            int[] test = new int[15000];
            for (int i = 0; i < 15000; i++)
            {
                test[i] = i + 1;
            }
            Assert.Equal(56250000, new Solution().MaxArea(test));
        }

        [Fact]
        public void TestHuh()
        {
            int[] test = { 1, 2, 1, 15, 15, 1, 2, 1 };
            Assert.Equal(15, new Solution().MaxArea(test));
        }
    }
}
