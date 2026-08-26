package lc203

import "testing"

func convert(array []int) *ListNode {
	handle := &ListNode{}
	last := handle
	for _, value := range array {
		node := &ListNode{Val: value}
		last.Next = node
		last = node
	}
	return handle.Next
}

func Test612345676And6(t *testing.T) {
	array := []int{6, 1, 2, 3, 4, 5, 6, 7, 6}
	listed := convert(array)
	listed = removeElements(listed, 6)
	expected := []int{1, 2, 3, 4, 5, 7}
	for _, value := range expected {
		if listed == nil || value != listed.Val {
			t.Errorf("RemoveElements - Expected %v, got %v!", value, listed)
			return
		}
		listed = listed.Next
	}
}

func TestNothing(t *testing.T) {
	if removeElements(nil, 0) != nil {
		t.Errorf("RemoveElements - Expected nil, got something!")
	}
}
