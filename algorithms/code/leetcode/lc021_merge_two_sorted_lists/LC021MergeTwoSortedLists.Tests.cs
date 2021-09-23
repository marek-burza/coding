using Xunit;

namespace AlgorithmDesign.code.leetcode.lc021_merge_two_sorted_lists
{
    public class SolutionTests
    {
        [Fact]
        public void test_1_3_5_7_9__2_4_6()
        {
            ListNode n9 = new ListNode { val = 9, next = null };
            ListNode n7 = new ListNode { val = 7, next = n9 };
            ListNode n5 = new ListNode { val = 5, next = n7 };
            ListNode n3 = new ListNode { val = 3, next = n5 };
            ListNode n1 = new ListNode { val = 1, next = n3 };
            ListNode n6 = new ListNode { val = 6, next = null };
            ListNode n4 = new ListNode { val = 4, next = n6 };
            ListNode n2 = new ListNode { val = 2, next = n4 };
            ListNode n0 = new Solution().MergeTwoLists(n1, n2);
            Assert.Equal(1, n0.val);
            Assert.Equal(2, n0.next.val);
            Assert.Equal(3, n0.next.next.val);
            Assert.Equal(4, n0.next.next.next.val);
            Assert.Equal(5, n0.next.next.next.next.val);
            Assert.Equal(6, n0.next.next.next.next.next.val);
            Assert.Equal(7, n0.next.next.next.next.next.next.val);
            Assert.Equal(9, n0.next.next.next.next.next.next.next.val);
        }

        [Fact]
        public void test_1_2_3__4_5_6()
        {
            ListNode n3 = new ListNode { val = 3, next = null };
            ListNode n2 = new ListNode { val = 2, next = n3 };
            ListNode n1 = new ListNode { val = 1, next = n2 };
            ListNode n6 = new ListNode { val = 6, next = null };
            ListNode n5 = new ListNode { val = 5, next = n6 };
            ListNode n4 = new ListNode { val = 4, next = n5 };
            ListNode n0 = new Solution().MergeTwoLists(n1, n4);
            Assert.Equal(1, n0.val);
            Assert.Equal(2, n0.next.val);
            Assert.Equal(3, n0.next.next.val);
            Assert.Equal(4, n0.next.next.next.val);
            Assert.Equal(5, n0.next.next.next.next.val);
            Assert.Equal(6, n0.next.next.next.next.next.val);
            Assert.Null(n0.next.next.next.next.next.next);
        }
    }
}
