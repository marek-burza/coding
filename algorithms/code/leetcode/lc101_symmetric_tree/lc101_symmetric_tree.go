// Package lc101 implements https://leetcode.com/problems/symmetric-tree/
package lc101

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func isSymmetric(root *TreeNode) bool {
	if root == nil {
		return true
	}
	var queue [][2]*TreeNode
	queue = append(queue, [2]*TreeNode{root, root})
	for len(queue) > 0 {
		node1, node2 := queue[0][0], queue[0][1]
		queue = queue[1:]
		if node1.Val != node2.Val {
			return false
		}
		node1Left, node2Right := node1.Left, node2.Right
		if node1Left != nil && node2Right != nil {
			queue = append(queue, [2]*TreeNode{node1Left, node2Right})
		} else if node1Left != nil || node2Right != nil {
			return false
		}
		node1Right, node2Left := node1.Right, node2.Left
		if node1Right != nil && node2Left != nil {
			queue = append(queue, [2]*TreeNode{node1Right, node2Left})
		} else if node1Right != nil || node2Left != nil {
			return false
		}
	}
	return true
}
