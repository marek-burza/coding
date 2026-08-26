package lc226

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n6 := &TreeNode{Val: 6}
	n7 := &TreeNode{Val: 7}
	n9 := &TreeNode{Val: 9}
	n4.Left = n2
	n4.Right = n7
	n2.Left = n1
	n2.Right = n3
	n7.Left = n6
	n7.Right = n9
	inverted := invertTree(n4)
	expected := &TreeNode{
		Val:   4,
		Left:  &TreeNode{Val: 7, Left: &TreeNode{Val: 9}, Right: &TreeNode{Val: 6}},
		Right: &TreeNode{Val: 2, Left: &TreeNode{Val: 3}, Right: &TreeNode{Val: 1}},
	}
	if !reflect.DeepEqual(expected, inverted) {
		t.Errorf("InvertTree - Expected %v, got %v!", expected, inverted)
	}
}
