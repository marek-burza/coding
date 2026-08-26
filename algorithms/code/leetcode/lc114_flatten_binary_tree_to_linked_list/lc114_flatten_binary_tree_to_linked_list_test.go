package lc114

import (
	"reflect"
	"testing"
)

func generic(t *testing.T, expected *TreeNode, root *TreeNode) {
	if !reflect.DeepEqual(expected, root) {
		t.Errorf("Flatten - Expected %v, got %v!", expected, root)
	}
}

func TestExample(t *testing.T) {
	root := &TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Left.Left = &TreeNode{Val: 3}
	root.Left.Right = &TreeNode{Val: 4}
	root.Right = &TreeNode{Val: 5}
	root.Right.Right = &TreeNode{Val: 6}
	expected := &TreeNode{Val: 1}
	expected.Right = &TreeNode{Val: 2}
	expected.Right.Right = &TreeNode{Val: 3}
	expected.Right.Right.Right = &TreeNode{Val: 4}
	expected.Right.Right.Right.Right = &TreeNode{Val: 5}
	expected.Right.Right.Right.Right.Right = &TreeNode{Val: 6}
	flatten(root)
	generic(t, expected, root)
}

func TestOther1(t *testing.T) {
	root := &TreeNode{Val: 1}
	root.Right = &TreeNode{Val: 2}
	root.Right.Left = &TreeNode{Val: 3}
	expected := &TreeNode{Val: 1}
	expected.Right = &TreeNode{Val: 2}
	expected.Right.Right = &TreeNode{Val: 3}
	flatten(root)
	generic(t, expected, root)
}

func TestOther2(t *testing.T) {
	root := &TreeNode{Val: 3}
	root.Left = &TreeNode{Val: 1}
	root.Left.Left = &TreeNode{Val: 4}
	root.Left.Left.Right = &TreeNode{Val: 2}
	expected := &TreeNode{Val: 3}
	expected.Right = &TreeNode{Val: 1}
	expected.Right.Right = &TreeNode{Val: 4}
	expected.Right.Right.Right = &TreeNode{Val: 2}
	flatten(root)
	generic(t, expected, root)
}
