// Package lc083 implements https://leetcode.com/problems/remove-duplicates-from-sorted-list/
package lc083

import "slices"

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

func linkedToListed(linked *ListNode) []int {
	var listed []int
	for linked != nil {
		listed = append(listed, linked.Val)
		linked = linked.Next
	}
	return listed
}

func listedToLinked(listed []int) *ListNode {
	var linked *ListNode
	for _, l := range slices.Backward(listed) {
		linked = &ListNode{Val: l, Next: linked}
	}
	return linked
}

func deleteDuplicates(head *ListNode) *ListNode {
	listed := linkedToListed(head)
	var deduplicated []int
	for _, value := range listed {
		if len(deduplicated) == 0 || deduplicated[len(deduplicated)-1] != value {
			deduplicated = append(deduplicated, value)
		}
	}
	return listedToLinked(deduplicated)
}
