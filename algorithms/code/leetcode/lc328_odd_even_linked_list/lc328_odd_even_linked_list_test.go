package lc328

import "testing"

func TestExample(t *testing.T) {
	head := &ListNode{}
	tail := head
	for i := 1; i < 6; i++ {
		tail.Next = &ListNode{Val: i}
		tail = tail.Next
	}
	result := oddEvenList(head.Next)
	expected := []int{1, 3, 5, 2, 4}
	for _, value := range expected {
		if result == nil || value != result.Val {
			t.Errorf("OddEvenList - Expected %v, got %v!", value, result)
			return
		}
		result = result.Next
	}
	if result != nil {
		t.Errorf("OddEvenList - Expected nil, got %v!", result)
	}
}

func TestNull(t *testing.T) {
	if oddEvenList(nil) != nil {
		t.Errorf("OddEvenList - Expected nil, got something!")
	}
}
