// Package lc155 implements https://leetcode.com/problems/min-stack/
package lc155

// MinStack Defines a stack keeping track of its smallest value
type MinStack struct {
	stack    []int
	minStack []int
}

// Push Adds the value on top of the stack
func (ms *MinStack) Push(val int) {
	ms.stack = append(ms.stack, val)
	if len(ms.minStack) == 0 || val <= ms.minStack[len(ms.minStack)-1] {
		ms.minStack = append(ms.minStack, val)
	}
}

// Pop Drops the value from the top of the stack
func (ms *MinStack) Pop() {
	if len(ms.stack) == 0 {
		return
	}
	if ms.stack[len(ms.stack)-1] == ms.minStack[len(ms.minStack)-1] {
		ms.minStack = ms.minStack[:len(ms.minStack)-1]
	}
	ms.stack = ms.stack[:len(ms.stack)-1]
}

// Top Returns the value from the top of the stack or -1 when there is none
func (ms *MinStack) Top() int {
	if len(ms.stack) == 0 {
		return -1
	}
	return ms.stack[len(ms.stack)-1]
}

// GetMin Returns the smallest value on the stack or -1 when there is none
func (ms *MinStack) GetMin() int {
	if len(ms.minStack) == 0 {
		return -1
	}
	return ms.minStack[len(ms.minStack)-1]
}
