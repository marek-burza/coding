// Package lc108 implements https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
package lc108

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func sortedArrayToBSTInternal(nums []int, head int, tail int) *TreeNode {
	if head >= tail {
		return nil
	}
	length := tail - head
	half := head + (length >> 1)
	rootLeft := sortedArrayToBSTInternal(nums, head, half)
	rootRight := sortedArrayToBSTInternal(nums, half+1, tail)
	return &TreeNode{Val: nums[half], Left: rootLeft, Right: rootRight}
}

func sortedArrayToBST(nums []int) *TreeNode {
	return sortedArrayToBSTInternal(nums, 0, len(nums))
}
