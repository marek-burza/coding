package lc002

import (
	"testing"
)

func thaw(array []int) *ListNode {
	handle := &ListNode{}
	tail := handle
	for _, value := range array {
		tail.Next = &ListNode{Val: value}
		tail = tail.Next
	}
	return handle.Next
}

func freeze(list *ListNode) []int {
	var result []int
	for list != nil {
		result = append(result, list.Val)
		list = list.Next
	}
	return result
}

func equal(a, b []int) bool {
	if len(a) != len(b) {
		return false
	}
	for i := range a {
		if a[i] != b[i] {
			return false
		}
	}
	return true
}

func TestExample(t *testing.T) {
	array1 := []int{2, 4, 3}
	array2 := []int{5, 6, 4}
	list := addTwoNumbers(thaw(array1), thaw(array2))
	expected := []int{7, 0, 8}
	if got := freeze(list); !equal(got, expected) {
		t.Errorf("AddTwoNumbers - TestExample() = %v; want %v", got, expected)
	}
}

func TestUneven(t *testing.T) {
	array1 := []int{2, 4}
	array2 := []int{5, 6, 4}
	list := addTwoNumbers(thaw(array1), thaw(array2))
	expected := []int{7, 0, 5}
	if got := freeze(list); !equal(got, expected) {
		t.Errorf("AddTwoNumbers - TestUneven() = %v; want %v", got, expected)
	}
}

func TestCarry(t *testing.T) {
	array1 := []int{2, 4}
	array2 := []int{5, 6}
	list := addTwoNumbers(thaw(array1), thaw(array2))
	expected := []int{7, 0, 1}
	if got := freeze(list); !equal(got, expected) {
		t.Errorf("AddTwoNumbers - TestCarry() = %v; want %v", got, expected)
	}
}
