using Xunit;

namespace AlgorithmDesign.leetcode.lc024_swap_nodes_in_pairs
{
    public class SolutionTests
    {
        [Fact]
        public void test_example()
        {
            ListNode n1 = new ListNode(1);
            ListNode n2 = new ListNode(2);
            ListNode n3 = new ListNode(3);
            ListNode n4 = new ListNode(4);
            n1.next = n2;
            n2.next = n3;
            n3.next = n4;
            int[] expected = { 2, 1, 4, 3 };
            ListNode result = new Solution().SwapPairs(n1);
            foreach (int value in expected)
            {
                Assert.NotNull(result);
                Assert.Equal(value, result.val);
                result = result.next;
            }
        }
    }
}
