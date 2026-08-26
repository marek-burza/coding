// Package lc199 implements https://leetcode.com/problems/binary-tree-right-side-view/
package lc199

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func rightSideViewInternal(root *TreeNode, level int, listed *[]int) {
	if root != nil {
		level++
		if level > len(*listed) {
			*listed = append(*listed, root.Val)
		}
		rightSideViewInternal(root.Right, level, listed)
		rightSideViewInternal(root.Left, level, listed)
	}
}

func rightSideView(root *TreeNode) []int {
	var listed []int
	rightSideViewInternal(root, 0, &listed)
	return listed
}
