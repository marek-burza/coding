// Package lc378 implements https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
// #medium
package lc378

import "container/heap"

type item struct {
	value int
}

type itemHeap []*item

func (h itemHeap) Len() int           { return len(h) }
func (h itemHeap) Less(i, j int) bool { return h[j].value < h[i].value }
func (h itemHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *itemHeap) Push(value any)    { *h = append(*h, value.(*item)) }
func (h *itemHeap) Pop() any {
	old := *h
	value := old[len(old)-1]
	*h = old[:len(old)-1]
	return value
}

func kthSmallest(matrix [][]int, k int) int {
	h := &itemHeap{}
	for _, row := range matrix {
		for _, cell := range row {
			heap.Push(h, &item{cell})
			if h.Len() > k {
				heap.Pop(h)
			}
		}
	}
	return heap.Pop(h).(*item).value
}
