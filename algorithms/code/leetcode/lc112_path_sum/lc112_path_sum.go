// Package lc112 implements https://leetcode.com/problems/path-sum/
package lc112

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func hasPathSum(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	reduced := targetSum - root.Val
	if root.Left == nil && root.Right == nil {
		return reduced == 0
	}
	leftHasPathSum := hasPathSum(root.Left, reduced)
	rightHasPathSum := hasPathSum(root.Right, reduced)
	return leftHasPathSum || rightHasPathSum
}
