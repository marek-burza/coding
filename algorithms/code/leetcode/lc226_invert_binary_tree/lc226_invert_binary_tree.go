// Package lc226 implements https://leetcode.com/problems/invert-binary-tree/
package lc226

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func invertTree(root *TreeNode) *TreeNode {
	if root != nil {
		node := root.Left
		root.Left = root.Right
		root.Right = node
		invertTree(root.Left)
		invertTree(root.Right)
	}
	return root
}
