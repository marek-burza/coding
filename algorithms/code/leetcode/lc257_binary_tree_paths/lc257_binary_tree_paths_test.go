package lc257

import (
	"reflect"
	"sort"
	"testing"
)

func generic(t *testing.T, expected []string, result []string) {
	sort.Strings(result)
	if !reflect.DeepEqual(expected, result) {
		t.Errorf("BinaryTreePaths - Expected %v, got %v!", expected, result)
	}
}

func TestExample(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n5 := &TreeNode{Val: 5}
	n1.Left = n2
	n1.Right = n3
	n2.Right = n5
	expected := []string{"1->2->5", "1->3"}
	generic(t, expected, binaryTreePaths(n1))
}

func TestExampleMirrored(t *testing.T) {
	n1 := &TreeNode{Val: 1}
	n2 := &TreeNode{Val: 2}
	n3 := &TreeNode{Val: 3}
	n5 := &TreeNode{Val: 5}
	n1.Right = n2
	n1.Left = n3
	n2.Left = n5
	expected := []string{"1->2->5", "1->3"}
	generic(t, expected, binaryTreePaths(n1))
}

func TestNothing(t *testing.T) {
	if len(binaryTreePaths(nil)) != 0 {
		t.Errorf("BinaryTreePaths - Expected nothing!")
	}
}
