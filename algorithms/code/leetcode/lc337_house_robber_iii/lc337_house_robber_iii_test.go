package lc337

import "testing"

func generic(t *testing.T, result int, expected int) {
	if expected != result {
		t.Errorf("Rob - Expected %v, got %v!", expected, result)
	}
}

func TestExample1(t *testing.T) {
	t3 := &TreeNode{Val: 3}
	l2 := &TreeNode{Val: 2}
	lr3 := &TreeNode{Val: 3}
	r3 := &TreeNode{Val: 3}
	rr1 := &TreeNode{Val: 1}
	t3.Left = l2
	t3.Right = r3
	t3.Left.Right = lr3
	t3.Right.Right = rr1
	generic(t, rob(t3), 7)
}

func TestExample2(t *testing.T) {
	t3 := &TreeNode{Val: 3}
	l4 := &TreeNode{Val: 4}
	ll1 := &TreeNode{Val: 1}
	lr3 := &TreeNode{Val: 3}
	r5 := &TreeNode{Val: 5}
	rr1 := &TreeNode{Val: 1}
	t3.Left = l4
	t3.Right = r5
	t3.Left.Left = ll1
	t3.Left.Right = lr3
	t3.Right.Right = rr1
	generic(t, rob(t3), 9)
}
