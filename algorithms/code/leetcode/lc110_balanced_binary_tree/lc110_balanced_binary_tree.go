// Package lc110 implements https://leetcode.com/problems/balanced-binary-tree/
package lc110

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func abs(value int) int {
	if value < 0 {
		value = -value
	}
	return value
}

func balancedHeight(root *TreeNode) int {
	if root == nil {
		return 0
	}
	left := balancedHeight(root.Left)
	if left == -1 {
		return -1
	}
	right := balancedHeight(root.Right)
	if right == -1 {
		return -1
	}
	if abs(left-right) > 1 {
		return -1
	}
	return 1 + max(left, right)
}

func isBalanced(root *TreeNode) bool {
	return balancedHeight(root) != -1
}
