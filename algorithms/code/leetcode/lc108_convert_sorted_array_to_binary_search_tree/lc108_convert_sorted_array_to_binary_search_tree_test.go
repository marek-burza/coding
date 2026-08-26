package lc108

import (
	"math"
	"testing"
)

func findExtreme(root *TreeNode, init float64, relation func(float64, float64) float64) float64 {
	if root == nil {
		return 0
	}
	nodes := []*TreeNode{root}
	levels := []int{1}
	extremum := init
	for len(nodes) > 0 {
		node := nodes[0]
		nodes = nodes[1:]
		level := levels[0]
		levels = levels[1:]
		if node.Left == nil && node.Right == nil {
			extremum = relation(float64(level), extremum)
		} else {
			if node.Left != nil {
				nodes = append(nodes, node.Left)
				levels = append(levels, level+1)
			}
			if node.Right != nil {
				nodes = append(nodes, node.Right)
				levels = append(levels, level+1)
			}
		}
	}
	return extremum
}

func minHeight(root *TreeNode) float64 {
	return findExtreme(root, math.Inf(1), math.Min)
}

func maxHeight(root *TreeNode) float64 {
	return findExtreme(root, math.Inf(-1), math.Max)
}

func reconstruct(root *TreeNode, listed *[]int) {
	if root == nil {
		return
	}
	if root.Left != nil {
		reconstruct(root.Left, listed)
	}
	*listed = append(*listed, root.Val)
	if root.Right != nil {
		reconstruct(root.Right, listed)
	}
}

func isBST(root *TreeNode) bool {
	var listed []int
	reconstruct(root, &listed)
	previous := math.Inf(-1)
	for _, value := range listed {
		if previous > float64(value) {
			return false
		}
		previous = float64(value)
	}
	return true
}

func TestEmpty(t *testing.T) {
	if sortedArrayToBST([]int{}) != nil {
		t.Errorf("SortedArrayToBST - Expected nil, got something!")
	}
}

func TestDepthAndOrdering(t *testing.T) {
	nums := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	root := sortedArrayToBST(nums)
	difference := maxHeight(root) - minHeight(root)
	if difference < 0 || difference > 1 {
		t.Errorf("SortedArrayToBST - Expected a balanced tree, got a difference of %v!", difference)
	}
	if !isBST(root) {
		t.Errorf("SortedArrayToBST - Expected a binary search tree!")
	}
}
