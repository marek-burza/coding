// Package lc206 implements https://leetcode.com/problems/reverse-linked-list/
package lc206

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func reverseList(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	var ante *ListNode
	for head != nil {
		post := head.Next
		head.Next = ante
		ante = head
		head = post
	}
	return ante
}
