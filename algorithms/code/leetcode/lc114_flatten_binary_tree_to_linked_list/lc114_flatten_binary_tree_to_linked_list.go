// Package lc114 implements https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// #medium
package lc114

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func flatten(root *TreeNode) {
	for root != nil {
		if root.Left != nil {
			node := root.Left
			for node.Right != nil {
				node = node.Right
			}
			node.Right = root.Right
			root.Right = root.Left
			root.Left = nil
		}
		root = root.Right
	}
}
