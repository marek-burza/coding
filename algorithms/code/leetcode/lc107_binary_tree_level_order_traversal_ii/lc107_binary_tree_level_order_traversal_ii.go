// Package lc107 implements https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
// #medium
package lc107

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func levelOrderBottom(root *TreeNode) [][]int {
	var result [][]int
	var current []*TreeNode
	if root != nil {
		current = append(current, root)
	}
	for len(current) > 0 {
		var level []int
		var future []*TreeNode
		for _, node := range current {
			level = append(level, node.Val)
			if node.Left != nil {
				future = append(future, node.Left)
			}
			if node.Right != nil {
				future = append(future, node.Right)
			}
		}
		result = append(result, level)
		current = future
	}
	length := len(result)
	for i := range length / 2 {
		result[i], result[length-1-i] = result[length-1-i], result[i]
	}
	return result
}
