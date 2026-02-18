// Package lc002 implements https://leetcode.com/problems/add-two-numbers/
package lc002

// ListNode defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1, l2 *ListNode) *ListNode {
	handle := &ListNode{}
	tail := handle
	carry := 0
	for l1 != nil || l2 != nil || carry != 0 {
		value1, value2 := 0, 0
		if l1 != nil {
			value1 = l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			value2 = l2.Val
			l2 = l2.Next
		}
		sum := carry + value1 + value2
		tail.Next = &ListNode{Val: sum % 10}
		tail = tail.Next
		carry = sum / 10
	}
	return handle.Next
}
