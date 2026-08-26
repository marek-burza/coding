// Package lc232 implements https://leetcode.com/problems/implement-queue-using-stacks/
package lc232

// MyQueue Defines a queue backed by a pair of stacks
type MyQueue struct {
	stack  []int
	buffer []int
}

// Push Pushes element x to the back of queue
func (mq *MyQueue) Push(x int) {
	for len(mq.stack) > 0 {
		mq.buffer = append(mq.buffer, mq.stack[len(mq.stack)-1])
		mq.stack = mq.stack[:len(mq.stack)-1]
	}
	mq.stack = append(mq.stack, x)
	for len(mq.buffer) > 0 {
		mq.stack = append(mq.stack, mq.buffer[len(mq.buffer)-1])
		mq.buffer = mq.buffer[:len(mq.buffer)-1]
	}
}

// Pop Removes the element from in front of queue
func (mq *MyQueue) Pop() int {
	value := mq.stack[len(mq.stack)-1]
	mq.stack = mq.stack[:len(mq.stack)-1]
	return value
}

// Peek Gets the front element
func (mq *MyQueue) Peek() int {
	return mq.stack[len(mq.stack)-1]
}

// Empty Returns whether the queue is empty
func (mq *MyQueue) Empty() bool {
	return len(mq.stack) == 0
}
