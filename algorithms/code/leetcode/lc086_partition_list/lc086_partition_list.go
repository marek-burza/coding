// Package lc086 implements https://leetcode.com/problems/partition-list/
// #medium
package lc086

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func partition(head *ListNode, x int) *ListNode {
	if head == nil {
		return nil
	}
	less := &ListNode{}
	more := &ListNode{}
	before := less
	after := more
	for head != nil {
		if head.Val < x {
			less.Next = head
			less = head
		} else {
			more.Next = head
			more = head
		}
		head = head.Next
	}
	less.Next = after.Next
	more.Next = nil
	return before.Next
}
