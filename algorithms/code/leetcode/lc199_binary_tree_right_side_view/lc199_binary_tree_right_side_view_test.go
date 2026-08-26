package lc199

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n5 := &TreeNode{Val: 5}
	n6 := &TreeNode{Val: 6}
	n1.Left = n2
	n1.Right = n3
	n2.Right = n5
	n3.Right = n4
	n5.Left = n6
	expected := []int{1, 3, 4, 6}
	result := rightSideView(n1)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("RightSideView - Expected %v, got %v!", expected, result)
	}
}
