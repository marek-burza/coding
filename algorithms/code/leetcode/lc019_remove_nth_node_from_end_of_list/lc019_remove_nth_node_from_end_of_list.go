// Package lc019 implements https://leetcode.com/problems/remove-nth-node-from-end-of-list/
package lc019

// ListNode defines a single-linked list node
type ListNode struct {
	Val  int
	Next *ListNode
}

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	// Translate the index number from counted from the back to a one counted from the front
	node := head
	for node != nil {
		n--
		node = node.Next
	}
	n = -n
	// Do the deletion
	node = &ListNode{Next: head}
	head = node
	for head != nil {
		if n == 0 {
			if head.Next != nil {
				head.Next = head.Next.Next
				break
			}
		}
		head = head.Next
		n--
	}
	return node.Next
}
