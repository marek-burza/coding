package lc110

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsBalanced - Expected %v, got %v!", expected, result)
	}
}

func TestBalanced(t *testing.T) {
	left := &TreeNode{Val: 2, Left: &TreeNode{Val: 1}, Right: &TreeNode{Val: 3}}
	right := &TreeNode{Val: 6, Left: &TreeNode{Val: 5}, Right: &TreeNode{Val: 7}}
	root := &TreeNode{Val: 4, Left: left, Right: right}
	generic(t, isBalanced(root), true)
}

func TestImbalancedRight(t *testing.T) {
	right := &TreeNode{Val: 6, Left: &TreeNode{Val: 5}, Right: &TreeNode{Val: 7}}
	root := &TreeNode{Val: 4, Right: right}
	generic(t, isBalanced(root), false)
}

func TestImbalancedLeft(t *testing.T) {
	left := &TreeNode{Val: 2, Left: &TreeNode{Val: 1}, Right: &TreeNode{Val: 3}}
	root := &TreeNode{Val: 4, Right: left}
	generic(t, isBalanced(root), false)
}

func TestImbalancedDeepLeft(t *testing.T) {
	left := &TreeNode{Val: 2, Left: &TreeNode{Val: 1, Left: &TreeNode{Val: 3}}}
	right := &TreeNode{Val: 6, Left: &TreeNode{Val: 5}, Right: &TreeNode{Val: 7}}
	root := &TreeNode{Val: 4, Left: left, Right: right}
	generic(t, isBalanced(root), false)
}

func TestImbalancedDeepRight(t *testing.T) {
	left := &TreeNode{Val: 2, Left: &TreeNode{Val: 1}, Right: &TreeNode{Val: 3}}
	right := &TreeNode{Val: 6, Left: &TreeNode{Val: 5, Right: &TreeNode{Val: 7}}}
	root := &TreeNode{Val: 4, Left: left, Right: right}
	generic(t, isBalanced(root), false)
}
