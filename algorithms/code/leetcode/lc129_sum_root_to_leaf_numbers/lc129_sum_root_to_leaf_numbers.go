// Package lc129 implements https://leetcode.com/problems/sum-root-to-leaf-numbers/
package lc129

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func sumNumbersInternal(root *TreeNode, prefix int) int {
	if root == nil {
		return 0
	}
	prefix = prefix*10 + root.Val
	if root.Left == nil && root.Right == nil {
		return prefix
	}
	left := sumNumbersInternal(root.Left, prefix)
	right := sumNumbersInternal(root.Right, prefix)
	return left + right
}

func sumNumbers(root *TreeNode) int {
	return sumNumbersInternal(root, 0)
}
