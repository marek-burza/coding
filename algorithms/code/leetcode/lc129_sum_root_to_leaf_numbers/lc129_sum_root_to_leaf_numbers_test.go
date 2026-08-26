package lc129

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("SumNumbers - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	root := &TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 3}
	generic(t, sumNumbers(root), 25)
}

func TestNothing(t *testing.T) {
	generic(t, sumNumbers(nil), 0)
}

func TestLeft(t *testing.T) {
	root := &TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	generic(t, sumNumbers(root), 12)
}

func TestRight(t *testing.T) {
	root := &TreeNode{Val: 1}
	root.Right = &TreeNode{Val: 3}
	generic(t, sumNumbers(root), 13)
}
