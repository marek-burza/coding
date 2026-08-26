// Package lc109 implements https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
// #medium
package lc109

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func linkedToListed(linked *ListNode) []int {
	var listed []int
	for linked != nil {
		listed = append(listed, linked.Val)
		linked = linked.Next
	}
	return listed
}

func generate(listed []int, head int, tail int) *TreeNode {
	if head >= tail {
		return nil
	}
	length := tail - head
	half := head + (length >> 1)
	rootLeft := generate(listed, head, half)
	rootRight := generate(listed, half+1, tail)
	return &TreeNode{Val: listed[half], Left: rootLeft, Right: rootRight}
}

func sortedListToBST(head *ListNode) *TreeNode {
	listed := linkedToListed(head)
	return generate(listed, 0, len(listed))
}
