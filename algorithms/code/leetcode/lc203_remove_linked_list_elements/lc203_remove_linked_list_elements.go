// Package lc203 implements https://leetcode.com/problems/remove-linked-list-elements/
package lc203

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func removeElements(head *ListNode, val int) *ListNode {
	if head == nil {
		return nil
	}
	var previous *ListNode
	node := head
	for node != nil {
		if node.Val == val {
			if previous == nil {
				head = node.Next
			} else {
				previous.Next = node.Next
			}
		} else {
			previous = node
		}
		node = node.Next
	}
	return head
}
