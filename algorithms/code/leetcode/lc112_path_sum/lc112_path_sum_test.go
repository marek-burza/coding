package lc112

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("HasPathSum - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n7 := &TreeNode{Val: 7}
	n13 := &TreeNode{Val: 13}
	n4a := &TreeNode{Val: 4, Right: n1}
	n8 := &TreeNode{Val: 8, Left: n13, Right: n4a}
	n11 := &TreeNode{Val: 11, Left: n7, Right: n2}
	n4b := &TreeNode{Val: 4, Left: n11}
	n5 := &TreeNode{Val: 5, Left: n4b, Right: n8}
	generic(t, hasPathSum(n5, 22), true)
}

func TestLeftBend(t *testing.T) {
	right := &TreeNode{Val: 1}
	left := &TreeNode{Val: 2, Right: right}
	root := &TreeNode{Val: 3, Left: left}
	generic(t, hasPathSum(root, 6), true)
}

func TestRightBend(t *testing.T) {
	left := &TreeNode{Val: 1}
	right := &TreeNode{Val: 2, Left: left}
	root := &TreeNode{Val: 3, Right: right}
	generic(t, hasPathSum(root, 6), true)
}

func TestNoPath(t *testing.T) {
	left := &TreeNode{Val: 0}
	right := &TreeNode{Val: 0}
	root := &TreeNode{Val: 0, Left: left, Right: right}
	generic(t, hasPathSum(root, 6), false)
}

func TestNothing(t *testing.T) {
	generic(t, hasPathSum(nil, 0), false)
}
