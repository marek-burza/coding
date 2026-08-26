package lc147

import (
	"reflect"
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

func generic(t *testing.T, result []int, expected []int) {
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("InsertionSortList - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	linked := listedToLinked([]int{6, 3, 4, 5, 2, 1})
	result := insertionSortList(linked)
	generic(t, linkedToListed(result), []int{1, 2, 3, 4, 5, 6})
}

func Test11(t *testing.T) {
	linked := listedToLinked([]int{1, 1})
	result := insertionSortList(linked)
	generic(t, linkedToListed(result), []int{1, 1})
}

func TestNothing(t *testing.T) {
	result := insertionSortList(nil)
	if result != nil {
		t.Errorf("InsertionSortList - Expected nil, got %v!", result)
	}
}
