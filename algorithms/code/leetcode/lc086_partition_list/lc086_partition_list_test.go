package lc086

import (
	"testing"
)

func build(array []int) *ListNode {
	var head *ListNode
	var tail *ListNode
	for _, value := range array {
		if head == nil {
			head = &ListNode{Val: value}
			tail = head
		} else {
			tail.Next = &ListNode{Val: value}
			tail = tail.Next
		}
	}
	return head
}

func Test143252And3(t *testing.T) {
	listed := build([]int{1, 4, 3, 2, 5, 2})
	expected := []int{1, 2, 2, 4, 3, 5}
	result := partition(listed, 3)
	for _, value := range expected {
		if result == nil || value != result.Val {
			t.Errorf("Partition - Expected %v, got %v!", value, result)
			return
		}
		result = result.Next
	}
}

func TestNothing(t *testing.T) {
	if partition(nil, 0) != nil {
		t.Errorf("Partition - Expected nil, got something!")
	}
}
