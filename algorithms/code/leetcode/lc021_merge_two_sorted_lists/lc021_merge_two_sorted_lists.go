package lc021

// https://leetcode.com/problems/merge-two-sorted-lists/

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	handle := &ListNode{}
	current := handle
	for l1 != nil && l2 != nil {
		if l1.Val < l2.Val {
			current.Next = l1
			l1 = l1.Next
		} else {
			current.Next = l2
			l2 = l2.Next
		}
		current = current.Next
	}
	if l1 != nil {
		current.Next = l1
	} else if l2 != nil {
		current.Next = l2
	}
	return handle.Next
}
