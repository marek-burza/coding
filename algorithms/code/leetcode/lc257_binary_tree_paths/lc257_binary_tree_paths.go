// Package lc257 implements https://leetcode.com/problems/binary-tree-paths/
package lc257

import "strconv"

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func binaryTreePathsInternal(root *TreeNode, prefix string, result *[]string) {
	if len(prefix) != 0 {
		prefix += "->"
	}
	prefix += strconv.Itoa(root.Val)
	if root.Left == nil && root.Right == nil {
		*result = append(*result, prefix)
	} else {
		if root.Left != nil {
			binaryTreePathsInternal(root.Left, prefix, result)
		}
		if root.Right != nil {
			binaryTreePathsInternal(root.Right, prefix, result)
		}
	}
}

func binaryTreePaths(root *TreeNode) []string {
	var result []string
	if root != nil {
		binaryTreePathsInternal(root, "", &result)
	}
	return result
}
