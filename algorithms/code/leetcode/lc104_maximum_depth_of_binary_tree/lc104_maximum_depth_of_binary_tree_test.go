package lc104

import "testing"

func TestExample(t *testing.T) {
	n0 := &TreeNode{Val: 0}
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n0.Left = n1
	n0.Right = n2
	n1.Right = n3
	expected := 3
	result := maxDepth(n0)
	if expected != result {
		t.Errorf("MaxDepth - Expected %v, got %v!", expected, result)
	}
}
