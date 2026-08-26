// Package lc382 implements https://leetcode.com/problems/linked-list-random-node/
// #medium
package lc382

import (
	"errors"
	"math/rand/v2"
)

// ListNode Defines a singly-linked list
type ListNode struct {
	Val  int
	Next *ListNode
}

// ErrEmptyList Reports that there is no node to pick from
var ErrEmptyList = errors.New("empty list")

// Solution Picks a random node of a linked list
type Solution struct {
	head *ListNode
}

// NewSolution Creates a picker over the given linked list
func NewSolution(head *ListNode) *Solution {
	return &Solution{head: head}
}

// GetRandom Returns the value of a uniformly picked node
func (solution *Solution) GetRandom() (int, error) {
	var result *ListNode
	current := solution.head
	i := 1
	for current != nil {
		if rand.IntN(i) == 0 {
			result = current
		}
		i++
		current = current.Next
	}
	if result != nil {
		return result.Val, nil
	}
	return 0, ErrEmptyList
}
