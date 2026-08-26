package lc206

import (
	"slices"
	"testing"
)

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

func generic(t *testing.T, linked *ListNode) {
	original := linkedToListed(linked)
	result := linkedToListed(reverseList(linked))
	if len(original) != len(result) {
		t.Errorf("ReverseList - Expected %v values, got %v!", len(original), len(result))
		return
	}
	for i := range original {
		if original[len(original)-1-i] != result[i] {
			t.Errorf("ReverseList - Expected %v, got %v!", original[len(original)-1-i], result[i])
		}
	}
}

func Test15(t *testing.T) {
	var listed []int
	for value := range 15 {
		listed = append(listed, value)
	}
	generic(t, listedToLinked(listed))
}

func Test1(t *testing.T) {
	generic(t, listedToLinked([]int{0}))
}

func TestNothing(t *testing.T) {
	if reverseList(nil) != nil {
		t.Errorf("ReverseList - Expected nil, got something!")
	}
}
