// Package lc328 implements https://leetcode.com/problems/odd-even-linked-list/
// #medium
package lc328

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func oddEvenList(head *ListNode) *ListNode {
	evenHead := &ListNode{}
	oddHead := &ListNode{}
	evenTail := evenHead
	oddTail := oddHead
	odd := true
	for head != nil {
		if odd {
			oddTail.Next = head
			oddTail = oddTail.Next
			odd = false
		} else {
			evenTail.Next = head
			evenTail = evenTail.Next
			odd = true
		}
		head = head.Next
	}
	evenTail.Next = nil
	oddTail.Next = evenHead.Next
	return oddHead.Next
}
