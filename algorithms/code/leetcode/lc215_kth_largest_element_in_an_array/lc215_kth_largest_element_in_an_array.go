// Package lc215 implements https://leetcode.com/problems/kth-largest-element-in-an-array/
// #medium
package lc215

import "container/heap"

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

func findKthLargest(nums []int, k int) int {
	h := &intHeap{}
	for _, num := range nums {
		if h.Len() < k || num > (*h)[0] {
			heap.Push(h, num)
			if h.Len() > k {
				heap.Pop(h)
			}
		}
	}
	return heap.Pop(h).(int)
}
