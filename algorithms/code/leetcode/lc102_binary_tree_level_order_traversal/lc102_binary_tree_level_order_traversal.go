// Package lc102 implements https://leetcode.com/problems/binary-tree-level-order-traversal/
// #medium
package lc102

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

func levelOrder(root *TreeNode) [][]int {
	var result [][]int
	var queue []annotatedNode
	if root != nil {
		queue = append(queue, annotatedNode{root, 1})
	}
	depth := 0
	for len(queue) > 0 {
		annotated := queue[0]
		queue = queue[1:]
		if depth != annotated.depth {
			depth = annotated.depth
			result = append(result, []int{})
		}
		result[len(result)-1] = append(result[len(result)-1], annotated.node.Val)
		if annotated.node.Left != nil {
			queue = append(queue, annotatedNode{annotated.node.Left, annotated.depth + 1})
		}
		if annotated.node.Right != nil {
			queue = append(queue, annotatedNode{annotated.node.Right, annotated.depth + 1})
		}
	}
	return result
}
