// Package lc264 implements https://leetcode.com/problems/super-ugly-number/
// #medium
package lc264

import (
	"container/heap"
)

type intHeap []int

func (h intHeap) Len() int           { return len(h) }
func (h intHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h intHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *intHeap) Push(value any)    { *h = append(*h, value.(int)) }
func (h *intHeap) Pop() any {
	old := *h
	value := old[len(old)-1]
	*h = old[:len(old)-1]
	return value
}

func nthUglyNumber(n int) int {
	if n == 1 {
		return 1
	}
	uglies := &intHeap{1}
	for range n - 1 {
		smallest := heap.Pop(uglies).(int)
		for uglies.Len() > 0 && (*uglies)[0] == smallest {
			heap.Pop(uglies)
		}
		heap.Push(uglies, smallest*2)
		heap.Push(uglies, smallest*3)
		heap.Push(uglies, smallest*5)
	}
	return (*uglies)[0]
}
