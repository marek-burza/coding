package lc109

import (
	"math"
	"slices"
	"testing"
)

func listedToLinked(listed []int) *ListNode {
	var linked *ListNode
	for _, l := range slices.Backward(listed) {
		linked = &ListNode{Val: l, Next: linked}
	}
	return linked
}

type minMax struct {
	min float64
	max float64
}

func depth(root *TreeNode, level int, depths *minMax) {
	if root == nil {
		if depths.max < float64(level) {
			depths.max = float64(level)
		}
		if depths.min > float64(level) {
			depths.min = float64(level)
		}
	} else {
		depth(root.Left, level+1, depths)
		depth(root.Right, level+1, depths)
	}
}

func generic(t *testing.T, root *TreeNode, linked *ListNode) *ListNode {
	if root != nil {
		linked = generic(t, root.Left, linked)
		if linked == nil || linked.Val != root.Val {
			t.Errorf("SortedListToBST - Expected %v, got %v!", root.Val, linked)
			return nil
		}
		linked = linked.Next
		linked = generic(t, root.Right, linked)
	}
	return linked
}

func TestBigger(t *testing.T) {
	var listed []int
	for value := -999; value <= 15340; value++ {
		listed = append(listed, value)
	}
	linked := listedToLinked(listed)
	root := sortedListToBST(linked)
	depths := &minMax{min: math.Inf(1), max: math.Inf(-1)}
	depth(root, 0, depths)
	if depths.max-depths.min >= 2 {
		t.Errorf("SortedListToBST - Expected a balanced tree, got depths %v to %v!", depths.min, depths.max)
	}
	if generic(t, root, linked) != nil {
		t.Errorf("SortedListToBST - Expected the whole list to be consumed!")
	}
}
