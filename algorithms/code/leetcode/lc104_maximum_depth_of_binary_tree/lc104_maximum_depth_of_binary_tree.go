// Package lc104 implements https://leetcode.com/problems/maximum-depth-of-binary-tree/
package lc104

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left := maxDepth(root.Left)
	right := maxDepth(root.Right)
	return 1 + max(left, right)
}
