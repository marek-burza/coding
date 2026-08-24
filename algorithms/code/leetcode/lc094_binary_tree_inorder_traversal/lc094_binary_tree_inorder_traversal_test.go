package lc094

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	node1 := &TreeNode{Val: 1}
	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node1.Right = node2
	node2.Left = node3
	result := inorderTraversal(node1)
	expected := []int{1, 3, 2}
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("InorderTraversal - Expected %v, got %v!", expected, result)
	}
}
