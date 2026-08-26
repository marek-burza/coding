// Package lc234 implements https://leetcode.com/problems/palindrome-linked-list/
package lc234

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func isPalindrome(head *ListNode) bool {
	var listed []int
	for head != nil {
		listed = append(listed, head.Val)
		head = head.Next
	}
	for i := range len(listed) / 2 {
		if listed[i] != listed[len(listed)-1-i] {
			return false
		}
	}
	return true
}
