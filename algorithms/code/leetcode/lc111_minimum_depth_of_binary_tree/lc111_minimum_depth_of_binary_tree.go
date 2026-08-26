// Package lc111 implements https://leetcode.com/problems/minimum-depth-of-binary-tree/
package lc111

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type annotatedNode struct {
	node  *TreeNode
	depth int
}

func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	var queue []annotatedNode
	queue = append(queue, annotatedNode{root, 1})
	for {
		annotated := queue[0]
		queue = queue[1:]
		if annotated.node.Left == nil && annotated.node.Right == nil {
			return annotated.depth
		}
		if annotated.node.Left != nil {
			queue = append(queue, annotatedNode{annotated.node.Left, annotated.depth + 1})
		}
		if annotated.node.Right != nil {
			queue = append(queue, annotatedNode{annotated.node.Right, annotated.depth + 1})
		}
	}
}
