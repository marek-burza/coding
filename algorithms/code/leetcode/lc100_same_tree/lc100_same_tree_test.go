package lc100

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsSameTree - Expected %v, got %v!", expected, result)
	}
}

func TestDifferent(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	bn0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	bn1 := &TreeNode{Val: 1}
	an2 := &TreeNode{Val: 2}
	bn2 := &TreeNode{Val: 3}
	an0.Left = an1
	an0.Right = an2
	bn0.Left = bn1
	bn0.Right = bn2
	generic(t, isSameTree(an0, bn0), false)
}

func TestSame(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	bn0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	bn1 := &TreeNode{Val: 1}
	an2 := &TreeNode{Val: 2}
	bn2 := &TreeNode{Val: 2}
	an0.Left = an1
	an0.Right = an2
	bn0.Left = bn1
	bn0.Right = bn2
	generic(t, isSameTree(an0, bn0), true)
}

func TestOneEmpty(t *testing.T) {
	tree := &TreeNode{Val: 0}
	generic(t, isSameTree(tree, nil), false)
	generic(t, isSameTree(nil, tree), false)
}

func TestBothEmpty(t *testing.T) {
	generic(t, isSameTree(nil, nil), true)
}

func TestLeft(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	bn0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	an0.Left = an1
	generic(t, isSameTree(an0, bn0), false)
}

func TestRight(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	bn0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	an0.Right = an1
	generic(t, isSameTree(an0, bn0), false)
}
