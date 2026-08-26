package lc107

import (
	"reflect"
	"testing"
)

func TestEmpty(t *testing.T) {
	result := levelOrderBottom(nil)
	if len(result) != 0 {
		t.Errorf("LevelOrderBottom - Expected nothing, got %v!", result)
	}
}

func TestExample(t *testing.T) {
	n3 := &TreeNode{Val: 3}
	n9 := &TreeNode{Val: 9}
	n20 := &TreeNode{Val: 20}
	n15 := &TreeNode{Val: 15}
	n7 := &TreeNode{Val: 7}
	n3.Left = n9
	n3.Right = n20
	n20.Left = n15
	n20.Right = n7
	expected := [][]int{{15, 7}, {9, 20}, {3}}
	result := levelOrderBottom(n3)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("LevelOrderBottom - Expected %v, got %v!", expected, result)
	}
}
