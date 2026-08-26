package lc230

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("KthSmallest - Expected %v, got %v!", expected, result)
	}
}

func TestLeft(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n4.Left = n3
	n3.Left = n2
	n2.Left = n1
	generic(t, kthSmallest(n4, 2), 2)
}

func TestRight(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n1.Right = n2
	n2.Right = n3
	n3.Right = n4
	generic(t, kthSmallest(n1, 2), 2)
}

func TestCoverage(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n5 := &TreeNode{Val: 5}
	n6 := &TreeNode{Val: 6}
	n2.Left = n1
	n2.Right = n3
	n4.Left = n2
	n4.Right = n5
	n5.Right = n6
	generic(t, kthSmallest(n4, 5), 5)
}
