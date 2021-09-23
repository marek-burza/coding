// https://leetcode.com/problems/swap-nodes-in-pairs/

namespace AlgorithmDesign.code.leetcode.lc024_swap_nodes_in_pairs
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
        public ListNode SwapPairs(ListNode head)
        {
            ListNode result = new ListNode { val = 0, next = null };
            result.next = head;
            head = result;
            while (head.next is not null && head.next.next is not null)
            {
                ListNode first = head.next;
                ListNode second = head.next.next;
                ListNode after = head.next.next.next;
                head.next = second;
                second.next = first;
                first.next = after;
                head = head.next.next;
            }
            return result.next;
        }
    }
}
