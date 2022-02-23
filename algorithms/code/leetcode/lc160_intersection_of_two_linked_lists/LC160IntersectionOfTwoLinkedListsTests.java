package leetcode.lc160_intersection_of_two_linked_lists;

import org.junit.jupiter.api.Test;

import leetcode.lc160_intersection_of_two_linked_lists.LC160IntersectionOfTwoLinkedLists.ListNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public final class LC160IntersectionOfTwoLinkedListsTests {
    @Test
    public void testExample() throws Exception {
        ListNode a1 = new ListNode(0xA1);
        ListNode a2 = new ListNode(0xA2);
        ListNode b1 = new ListNode(0xB1);
        ListNode b2 = new ListNode(0xB2);
        ListNode b3 = new ListNode(0xB3);
        ListNode c1 = new ListNode(0xC1);
        ListNode c2 = new ListNode(0xC2);
        ListNode c3 = new ListNode(0xC3);
        a1.next = a2;
        a2.next = c1;
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;
        c3.next = null;
        assertEquals(c1, new LC160IntersectionOfTwoLinkedLists().getIntersectionNode(a1, b1));
    }

    private ListNode genericExample(final ListNode l2) {
        ListNode l4 = new ListNode(4);
        ListNode l6 = new ListNode(6);
        l2.next = l6;
        l6.next = l4;
        ListNode l1 = new ListNode(1);
        ListNode l5 = new ListNode(5);
        l5.next = l1;
        return l5;
    }

    @Test
    public void testNonIntersecting() throws Exception {
        ListNode l2 = new ListNode(2);
        ListNode l5 = genericExample(l2);
        assertNull(new LC160IntersectionOfTwoLinkedLists().getIntersectionNode(l2, l5));
    }

    @Test
    public void testOtherNonIntersecting() throws Exception {
        ListNode l2 = new ListNode(2);
        ListNode l0 = new ListNode(0);
        ListNode l5 = genericExample(l2);
        l0.next = l5;
        assertNull(new LC160IntersectionOfTwoLinkedLists().getIntersectionNode(l2, l0));
    }

    @Test
    public void testOneEmpty() throws Exception {
        ListNode nonEmpty = new ListNode(0);
        assertNull(new LC160IntersectionOfTwoLinkedLists().getIntersectionNode(nonEmpty, null));
        assertNull(new LC160IntersectionOfTwoLinkedLists().getIntersectionNode(null, nonEmpty));
    }

    @Test
    public void testFieldUse() throws Exception {
        assertEquals(0, new ListNode(0).val);
    }
}
