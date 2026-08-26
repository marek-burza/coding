// Package lc147 implements https://leetcode.com/problems/insertion-sort-list/
// #medium
package lc147

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func insertionSortList(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	node := head
	// Grab the first node after the already ordered nodes
	tail := node
	// Iterate from the node holding the head
	handle := &ListNode{}
	// Iterate until we reach the end
	for node = tail; node != nil; node = tail {
		// Remove (extract) that node from the list
		tail = node.Next
		node.Next = nil
		// Grab the first ordered node
		current := handle
		// Iterate until we reach a node greater or equal
		// to the extracted one
		for current.Next != nil && current.Next.Val < node.Val {
			// Move on to the next ordered node
			current = current.Next
		}
		// Insert the node
		node.Next = current.Next
		current.Next = node
	}
	return handle.Next
}
