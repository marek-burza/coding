package lc102

import (
	"reflect"
	"testing"
)

func TestExample(t *testing.T) {
	n3 := &TreeNode{Val: 3}
	n7 := &TreeNode{Val: 7}
	n9 := &TreeNode{Val: 9}
	n15 := &TreeNode{Val: 15}
	n20 := &TreeNode{Val: 20}
	n3.Left = n9
	n3.Right = n20
	n20.Left = n15
	n20.Right = n7
	expected := [][]int{{3}, {9, 20}, {15, 7}}
	result := levelOrder(n3)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("LevelOrder - Expected %v, got %v!", expected, result)
	}
}

func TestNothing(t *testing.T) {
	result := levelOrder(nil)
	if len(result) != 0 {
		t.Errorf("LevelOrder - Expected nothing, got %v!", result)
	}
}
