using Xunit;

namespace AlgorithmDesign.code.leetcode.lc019_remove_nth_node_from_end_of_list
{
    public class SolutionTests
    {
        [Fact]
        public void test_1_2__1_1()
        {
            ListNode n1 = new ListNode(1);
            ListNode n2 = new ListNode(2);
            n1.next = n2;
            n2.next = null;
            ListNode n = n1;
            Solution solution = new Solution();
            Assert.Equal(1, n.val);
            Assert.Equal(2, n.next.val);
            n = solution.RemoveNthFromEnd(n, 1);
            Assert.Equal(1, n.val);
            n = solution.RemoveNthFromEnd(n, 1);
            Assert.Null(n);
        }

        [Fact]
        public void test_null__0()
        {
            Solution solution = new Solution();
            ListNode n = solution.RemoveNthFromEnd(null, 0);
            Assert.Null(n);
        }
    }
}
