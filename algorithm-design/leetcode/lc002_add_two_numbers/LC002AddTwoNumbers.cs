// https://leetcode.com/problems/add-two-numbers/

namespace AlgorithmDesign.leetcode.lc002_add_two_numbers
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
        public ListNode AddTwoNumbers(ListNode l1, ListNode l2)
        {
            ListNode handle = new ListNode();
            ListNode tail = handle;
            int carry = 0;
            while (l1 != null || l2 != null || carry != 0)
            {
                int value1 = 0, value2 = 0;
                if (l1 != null)
                {
                    value1 = l1.val;
                    l1 = l1.next;
                }
                if (l2 != null)
                {
                    value2 = l2.val;
                    l2 = l2.next;
                }
                int sum = carry + value1 + value2;
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
                carry = sum / 10;
            }
            return handle.next;
        }
    }
}
