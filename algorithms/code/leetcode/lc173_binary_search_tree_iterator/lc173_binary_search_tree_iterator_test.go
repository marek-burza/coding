package lc173

import "testing"

func TestExample(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n4 := &TreeNode{Val: 4}
	n5 := &TreeNode{Val: 5}
	n6 := &TreeNode{Val: 6}
	n7 := &TreeNode{Val: 7}
	n8 := &TreeNode{Val: 8}
	n9 := &TreeNode{Val: 9}
	n10 := &TreeNode{Val: 10}
	n11 := &TreeNode{Val: 11}
	n6.Left = n2
	n6.Right = n10
	n2.Left = n1
	n2.Right = n4
	n4.Left = n3
	n4.Right = n5
	n10.Left = n9
	n10.Right = n11
	n9.Left = n8
	n8.Left = n7
	iterator := NewBSTIterator(n6)
	i := 1
	for iterator.HasNext() {
		result := iterator.Next()
		if i != result {
			t.Errorf("BSTIterator - Expected %v, got %v!", i, result)
		}
		i++
	}
}
