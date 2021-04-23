using Xunit;

namespace AlgorithmDesign.codility.equi
{
    public class SolutionTests
    {
        [Fact]
        public void TestExample()
        {
            Assert.Equal(1, new Solution().FindEquilibriumIndex(new[] { -1, 3, -4, 5, 1, -6, 2, 1 }));
        }

        [Fact]
        public void TestEmpty()
        {
            Assert.Equal(-1, new Solution().FindEquilibriumIndex(new int[] { }));
        }

        [Fact]
        public void TestInvalid()
        {
            Assert.Equal(-1, new Solution().FindEquilibriumIndex(new[] { 1, 2, 3, 4 }));
        }
    }
}
