// Package lc230 implements https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// #medium
package lc230

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func kthSmallestInternal(root *TreeNode, k int, counter *TreeNode) int {
	if root.Left != nil {
		result := kthSmallestInternal(root.Left, k, counter)
		if counter.Val == k {
			return result
		}
	}
	counter.Val++
	if counter.Val == k {
		return root.Val
	}
	if root.Right != nil {
		result := kthSmallestInternal(root.Right, k, counter)
		if counter.Val == k {
			return result
		}
	}
	return 0
}

func kthSmallest(root *TreeNode, k int) int {
	counter := &TreeNode{}
	return kthSmallestInternal(root, k, counter)
}
