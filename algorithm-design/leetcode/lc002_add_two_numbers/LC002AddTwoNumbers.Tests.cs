using Xunit;

namespace AlgorithmDesign.leetcode.lc002_add_two_numbers
{
    public class Lc002AddTwoNumbersTests
    {
        [Fact]
        public void Test1()
        {

            Assert.True(new Lc002AddTwoNumbers().Add(1, 2) == 3);
        }
    }
}
