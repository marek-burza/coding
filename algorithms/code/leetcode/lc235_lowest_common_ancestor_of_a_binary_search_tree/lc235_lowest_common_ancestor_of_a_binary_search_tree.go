// Package lc235 implements https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
package lc235

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func lowestCommonAncestor(root *TreeNode, p *TreeNode, q *TreeNode) *TreeNode {
	if root != nil {
		var left *TreeNode
		var right *TreeNode
		if root.Left != nil {
			left = lowestCommonAncestor(root.Left, p, q)
		}
		if root.Right != nil {
			right = lowestCommonAncestor(root.Right, p, q)
		}
		if left != nil && left != p && left != q {
			return left
		}
		if right != nil && right != p && right != q {
			return right
		}
		gotP := root == p || left == p || right == p
		gotQ := root == q || left == q || right == q
		if gotP && gotQ {
			return root
		}
		if gotP {
			return p
		}
		if gotQ {
			return q
		}
	}
	return nil
}
