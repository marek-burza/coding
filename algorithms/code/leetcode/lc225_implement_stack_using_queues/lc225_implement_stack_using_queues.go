// Package lc225 implements https://leetcode.com/problems/implement-stack-using-queues/
package lc225

// MyStack Defines a stack backed by a pair of queues
type MyStack struct {
	active []int
	other  []int
}

func (ms *MyStack) swap() {
	ms.other, ms.active = ms.active, ms.other
}

// Push Pushes element x onto stack
func (ms *MyStack) Push(x int) {
	// 1 - active
	ms.other = append(ms.other, x)
	for len(ms.active) > 0 {
		ms.other = append(ms.other, ms.active[0])
		ms.active = ms.active[1:]
	}
	ms.swap()
}

// Pop Removes the element on top of the stack
func (ms *MyStack) Pop() int {
	value := ms.active[0]
	ms.active = ms.active[1:]
	return value
}

// Top Gets the top element
func (ms *MyStack) Top() int {
	return ms.active[0]
}

// Empty Returns whether the stack is empty
func (ms *MyStack) Empty() bool {
	return len(ms.active) == 0
}

// It's also possible with just one stack
