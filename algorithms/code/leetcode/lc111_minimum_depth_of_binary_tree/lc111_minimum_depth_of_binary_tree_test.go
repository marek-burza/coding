package lc111

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("MinDepth - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	n3 := &TreeNode{Val: 3}
	n7 := &TreeNode{Val: 7}
	n9 := &TreeNode{Val: 9}
	n15 := &TreeNode{Val: 15}
	n20 := &TreeNode{Val: 20}
	n3.Left = n9
	n3.Right = n20
	n20.Left = n15
	n20.Right = n7
	generic(t, minDepth(n3), 2)
}

func TestLeftNothing(t *testing.T) {
	root := &TreeNode{Val: 3}
	right := &TreeNode{Val: 7}
	root.Right = right
	generic(t, minDepth(root), 2)
}

func TestRightNothing(t *testing.T) {
	root := &TreeNode{Val: 3}
	left := &TreeNode{Val: 7}
	root.Right = left
	generic(t, minDepth(root), 2)
}

func TestNothing(t *testing.T) {
	generic(t, minDepth(nil), 0)
}
