package leetcode.lc086_partition_list;

import org.junit.jupiter.api.Test;

import leetcode.lc086_partition_list.LC086PartitionList.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC086PartitionListTests {
    private ListNode build(final int[] array) {
        ListNode head = null;
        ListNode tail = null;
        for (int value : array) {
            if (head == null) {
                tail = new ListNode(value);
                head = tail;
            } else {
                tail.next = new ListNode(value);
                tail = tail.next;
            }
        }
        if (tail != null) {
            tail.next = null;
        }
        return head;
    }

    @Test
    public void test143252And3() throws Exception {
        ListNode list = build(new int[] { 1, 4, 3, 2, 5, 2 });
        int[] expected = { 1, 2, 2, 4, 3, 5 };
        ListNode result = new LC086PartitionList().partition(list, 3);
        for (int value : expected) {
            assertEquals(value, result.val);
            result = result.next;
        }
    }

    @Test
    public void testNothing() throws Exception {
        assertNull(new LC086PartitionList().partition(null, 0));
    }
}
