// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

namespace AlgorithmDesign.leetcode.lc019_remove_nth_node_from_end_of_list
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
        public ListNode RemoveNthFromEnd(ListNode head, int n)
        {
            // Translate the index number from counted from the back to a one counted from the front
            ListNode node = head;
            while (node != null)
            {
                n--;
                node = node.next;
            }
            n = -n;
            // Do the deletion
            node = new ListNode { val = 0, next = null };
            node.next = head;
            head = node;
            while (head != null)
            {
                if (n == 0)
                {
                    if (head.next != null)
                    {
                        head.next = head.next.next;
                        break;
                    }
                }
                head = head.next;
                n--;
            }
            return node.next;
        }
    }
}
