// Package lc337 implements https://leetcode.com/problems/house-robber-iii/
// #medium
package lc337

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var robCache = make(map[*TreeNode]int)

func robCached(root *TreeNode) int {
	if root == nil {
		return 0
	}
	if value, found := robCache[root]; found {
		return value
	}
	excl := robCached(root.Left) + robCached(root.Right)
	incl := root.Val
	if root.Left != nil {
		incl += robCached(root.Left.Left) + robCached(root.Left.Right)
	}
	if root.Right != nil {
		incl += robCached(root.Right.Left) + robCached(root.Right.Right)
	}
	result := max(incl, excl)
	robCache[root] = result
	return result
}

func rob(root *TreeNode) int {
	return robCached(root)
}

// // Bottom-up
// func rob(root *TreeNode) int {
//     incl, excl := robInclExcl(root)
//     return max(incl, excl)
// }

// func robInclExcl(root *TreeNode) (int, int) {
//     if root == nil {
//         return 0, 0
//     }
//     inclL, exclL := robInclExcl(root.Left)
//     inclR, exclR := robInclExcl(root.Right)
//     incl := root.Val + exclL + exclR
//     excl := max(inclL, exclL) + max(inclR, exclR)
//     return incl, excl
// }
