// Package lc173 implements https://leetcode.com/problems/binary-search-tree-iterator/
// #medium
package lc173

// TreeNode Defines a binary tree node
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// BSTIterator Defines an in-order iterator over a binary search tree
type BSTIterator struct {
	stack []*TreeNode
}

// NewBSTIterator Creates an iterator positioned before the smallest value
func NewBSTIterator(root *TreeNode) *BSTIterator {
	iterator := &BSTIterator{}
	for root != nil {
		iterator.stack = append(iterator.stack, root)
		root = root.Left
	}
	return iterator
}

// HasNext Tells whether there are any values left to visit
func (iterator *BSTIterator) HasNext() bool {
	return len(iterator.stack) != 0
}

// Next Returns the next smallest value
func (iterator *BSTIterator) Next() int {
	nodeTop := iterator.stack[len(iterator.stack)-1]
	iterator.stack = iterator.stack[:len(iterator.stack)-1]
	result := nodeTop.Val
	node := nodeTop.Right
	for node != nil {
		iterator.stack = append(iterator.stack, node)
		node = node.Left
	}
	return result
}
