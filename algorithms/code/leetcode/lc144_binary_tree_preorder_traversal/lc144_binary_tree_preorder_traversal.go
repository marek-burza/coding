// Package lc144 implements https://leetcode.com/problems/binary-tree-preorder-traversal/
package lc144

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func preorderTraversalInternal(root *TreeNode, result *[]int) {
	if root == nil {
		return
	}
	*result = append(*result, root.Val)
	preorderTraversalInternal(root.Left, result)
	preorderTraversalInternal(root.Right, result)
}

func preorderTraversal(root *TreeNode) []int {
	var result []int
	preorderTraversalInternal(root, &result)
	return result
}
