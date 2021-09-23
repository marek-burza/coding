// https://leetcode.com/problems/merge-two-sorted-lists/

namespace AlgorithmDesign.code.leetcode.lc021_merge_two_sorted_lists
{
    // ReSharper Disable All
    public class ListNode
    {
        public int val;
        public ListNode next;
        public ListNode(int val = 0, ListNode next = null)
        {
            this.val = val;
            this.next = next;
        }
    }
    // ReSharper Restore All

    public class Solution
    {
        public ListNode MergeTwoLists(ListNode l1, ListNode l2)
        {
            ListNode handle = new ListNode();
            ListNode current = handle;
            current.next = null;
            while (l1 != null && l2 != null)
            {
                if (l1.val < l2.val)
                {
                    current.next = l1;
                    l1 = l1.next;
                }
                else
                {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }
            if (l1 != null)
            {
                current.next = l1;
            }
            if (l2 != null)
            {
                current.next = l2;
            }
            return handle.next;
        }
    }
}
