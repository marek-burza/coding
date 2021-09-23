using Xunit;

namespace AlgorithmDesign.code.leetcode.lc005_longest_palindromic_substring
{
    public class SolutionTests
    {
        [Fact]
        public void Test1()
        {
            Assert.Equal("bab", new Solution().LongestPalindrome("babad"));
        }

        [Fact]
        public void Test2()
        {
            Assert.Equal("bb", new Solution().LongestPalindrome("cbbd"));
        }

        [Fact]
        public void Test3()
        {
            Assert.Equal("a", new Solution().LongestPalindrome("a"));
        }

        [Fact]
        public void Test4()
        {
            Assert.Equal("a", new Solution().LongestPalindrome("ac"));
        }

        [Fact]
        public void TestBb()
        {
            Assert.Equal("bb", new Solution().LongestPalindrome("bb"));
        }

        [Fact]
        public void TestLonger()
        {
            Assert.Equal("ranynar", new Solution().LongestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
        }
    }
}
