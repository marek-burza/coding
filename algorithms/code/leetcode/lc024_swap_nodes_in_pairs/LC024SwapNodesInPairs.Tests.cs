using Xunit;

namespace AlgorithmDesign.code.leetcode.lc024_swap_nodes_in_pairs
{
    public class SolutionTests
    {
        private void Generic(ListNode head, int[] expected)
        {
            ListNode result = new Solution().SwapPairs(head);
            foreach (int value in expected)
            {
                Assert.NotNull(result);
                Assert.Equal(value, result.val);
                result = result.next;
            }
        }

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
            Generic(n1, expected);
        }

        [Fact]
        public void test_example_impaired()
        {
            ListNode n1 = new ListNode(1);
            ListNode n2 = new ListNode(2);
            ListNode n3 = new ListNode(3);
            n1.next = n2;
            n2.next = n3;
            int[] expected = { 2, 1, 3 };
            Generic(n1, expected);
        }
    }
}
