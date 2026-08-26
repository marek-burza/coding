// Package lc100 implements https://leetcode.com/problems/same-tree/
package lc100

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func isSameTree(tree1 *TreeNode, tree2 *TreeNode) bool {
	if tree1 == nil || tree2 == nil {
		return tree1 == nil && tree2 == nil
	}
	var queue [][2]*TreeNode
	queue = append(queue, [2]*TreeNode{tree1, tree2})
	for len(queue) > 0 {
		node1, node2 := queue[0][0], queue[0][1]
		queue = queue[1:]
		if node1.Val != node2.Val {
			return false
		}
		node1Left, node2Left := node1.Left, node2.Left
		if node1Left != nil && node2Left != nil {
			queue = append(queue, [2]*TreeNode{node1Left, node2Left})
		} else if node1Left != nil || node2Left != nil {
			return false
		}
		node1Right, node2Right := node1.Right, node2.Right
		if node1Right != nil && node2Right != nil {
			queue = append(queue, [2]*TreeNode{node1Right, node2Right})
		} else if node1Right != nil || node2Right != nil {
			return false
		}
	}
	return true
}
