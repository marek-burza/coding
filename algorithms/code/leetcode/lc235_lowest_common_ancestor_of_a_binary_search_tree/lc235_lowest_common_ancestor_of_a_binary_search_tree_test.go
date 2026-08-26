package lc235

import "testing"

func generic(t *testing.T, result *TreeNode, expected int) {
	if result == nil || result.Val != expected {
		t.Errorf("LowestCommonAncestor - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	n3 := &TreeNode{Val: 3}
	n5 := &TreeNode{Val: 5}
	n4 := &TreeNode{Val: 4, Left: n3, Right: n5}
	n0 := &TreeNode{Val: 0}
	n2 := &TreeNode{Val: 2, Left: n0, Right: n4}
	n7 := &TreeNode{Val: 7}
	n9 := &TreeNode{Val: 9}
	n8 := &TreeNode{Val: 8, Left: n7, Right: n9}
	n6 := &TreeNode{Val: 6, Left: n2, Right: n8}
	generic(t, lowestCommonAncestor(n6, n2, n8), 6)
	generic(t, lowestCommonAncestor(n2, n2, n4), 2)
}

func TestExample1(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2, Left: n1}
	n4 := &TreeNode{Val: 4}
	n3 := &TreeNode{Val: 3, Left: n2, Right: n4}
	n6 := &TreeNode{Val: 6}
	n5 := &TreeNode{Val: 5, Left: n3, Right: n6}
	generic(t, lowestCommonAncestor(n5, n1, n4), 3)
}

func TestExample2(t *testing.T) {
	n3 := &TreeNode{Val: 3}
	n5 := &TreeNode{Val: 5}
	n4 := &TreeNode{Val: 4, Left: n3, Right: n5}
	n7 := &TreeNode{Val: 7}
	n2 := &TreeNode{Val: 2, Left: n7, Right: n4}
	n9 := &TreeNode{Val: 9}
	n8 := &TreeNode{Val: 8, Right: n9}
	n6 := &TreeNode{Val: 6, Left: n2, Right: n8}
	generic(t, lowestCommonAncestor(n6, n3, n5), 4)
}

func TestExample3(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2, Left: n1}
	generic(t, lowestCommonAncestor(n2, n2, n1), 2)
}

func TestExample4(t *testing.T) {
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3, Right: n2}
	generic(t, lowestCommonAncestor(n2, n3, n2), 2)
}

func TestNothing(t *testing.T) {
	if lowestCommonAncestor(nil, nil, nil) != nil {
		t.Errorf("LowestCommonAncestor - Expected nil, got something!")
	}
}
