// Package lc094 implements https://leetcode.com/problems/binary-tree-inorder-traversal/
package lc094

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func preorderTraversal(root *TreeNode, result *[]int) {
	if root == nil {
		return
	}
	preorderTraversal(root.Left, result)
	*result = append(*result, root.Val)
	preorderTraversal(root.Right, result)
}

func inorderTraversal(root *TreeNode) []int {
	var result []int
	preorderTraversal(root, &result)
	return result
}
