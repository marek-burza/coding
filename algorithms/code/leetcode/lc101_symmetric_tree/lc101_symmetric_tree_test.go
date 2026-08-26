package lc101

import "testing"

func generic(t *testing.T, result bool, expected bool) {
	if expected != result {
		t.Errorf("IsSymmetric - Expected %v, got %v!", expected, result)
	}
}

func TestSymmetric(t *testing.T) {
	n0 := &TreeNode{Val: 0}
	n1a := &TreeNode{Val: 1}
	n1b := &TreeNode{Val: 1}
	n0.Left = n1a
	n0.Right = n1b
	generic(t, isSymmetric(n0), true)
}

func TestAsymmetric(t *testing.T) {
	n0 := &TreeNode{Val: 0}
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n0.Left = n1
	n0.Right = n2
	generic(t, isSymmetric(n0), false)
}

func TestEmpty(t *testing.T) {
	generic(t, isSymmetric(nil), true)
}

func TestLeft(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	an0.Left = an1
	generic(t, isSymmetric(an0), false)
}

func TestRight(t *testing.T) {
	an0 := &TreeNode{Val: 0}
	an1 := &TreeNode{Val: 1}
	an0.Right = an1
	generic(t, isSymmetric(an0), false)
}

func TestOther(t *testing.T) {
	n2 := &TreeNode{Val: 2}
	n3l := &TreeNode{Val: 3}
	n3r := &TreeNode{Val: 3}
	n4ll := &TreeNode{Val: 4}
	n5 := &TreeNode{Val: 5}
	n4rr := &TreeNode{Val: 4}
	n2.Left = n3l
	n2.Right = n3r
	n3l.Left = n4ll
	n3l.Right = n5
	n3r.Right = n4rr
	generic(t, isSymmetric(n2), false)
}
