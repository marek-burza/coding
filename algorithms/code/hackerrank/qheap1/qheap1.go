// Package qheap1 implements https://www.hackerrank.com/challenges/qheap1
package qheap1

import (
	"container/heap"
	"strconv"
)

func sheapChildLeft(index int) int {
	return (index << 1) + 1
}

func sheapChildRight(index int) int {
	return (index << 1) + 2
}

func sheapParent(index int) int {
	return (index - 1) >> 1
}

func sheapSwap(h []int, index1 int, index2 int) {
	h[index1], h[index2] = h[index2], h[index1]
}

func sheapIfyToLeaves(h []int, index int) {
	size := len(h)
	// Initialize smallest as root
	smallest := index
	// Left
	left := sheapChildLeft(index)
	// Right
	right := sheapChildRight(index)
	// Check if left child is smallest
	if left < size && h[left] < h[smallest] {
		smallest = left
	}
	// Check if right child is smallest
	if right < size && h[right] < h[smallest] {
		smallest = right
	}
	// If smallest is not root
	if smallest != index {
		// Swap
		sheapSwap(h, smallest, index)
		// Recursively heapify the affected sub-tree
		sheapIfyToLeaves(h, smallest)
	}
}

func sheapIfyToRoot(h []int, index int) {
	for index > 0 && h[sheapParent(index)] > h[index] {
		sheapSwap(h, index, sheapParent(index))
		index = sheapParent(index)
	}
}

// SheapBuild - turns an arbitrary slice into a heap
func SheapBuild(h []int) {
	size := len(h)
	index := (size / 2) - 1
	for index >= 0 {
		sheapIfyToLeaves(h, index)
		index--
	}
}

// SheapInsert - adds the value to the heap
func SheapInsert(h *[]int, value int) {
	*h = append(*h, value)
	index := len(*h) - 1
	sheapIfyToRoot(*h, index)
}

// SheapDeleteIndex - drops the value stored under the index
func SheapDeleteIndex(h *[]int, index int) {
	size := len(*h)
	sheapSwap(*h, index, size-1)
	*h = (*h)[:size-1] // Can't swap on a popped value so first swap
	if index == size-1 {
		return
	}
	if index == 0 || (*h)[sheapParent(index)] < (*h)[index] {
		sheapIfyToLeaves(*h, index)
	} else {
		sheapIfyToRoot(*h, index)
	}
}

// SheapDelete - drops the value from the heap
func SheapDelete(h *[]int, value int) {
	found, ok := SheapSearch(*h, value)
	if ok {
		SheapDeleteIndex(h, found)
	}
}

// SheapSearch - returns the index of the value and whether it was found
func SheapSearch(h []int, value int) (int, bool) {
	for index, num := range h {
		if num == value {
			return index, true
		}
	}
	return 0, false
	// Nodes at any level are unsorted
	// so stopping on first larger item might be premature
}

// SheapRoot - returns the smallest value of the heap
func SheapRoot(h []int) int {
	return h[0]
}

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

// QheapInsert - adds the value to the heap
func QheapInsert(h *[]int, value int) {
	inner := (*intHeap)(h)
	heap.Push(inner, value)
}

// QheapDelete - drops the value from the heap
func QheapDelete(h *[]int, value int) {
	inner := (*intHeap)(h)
	for index, num := range *inner {
		if num == value {
			heap.Remove(inner, index)
			return
		}
	}
}

// QheapRoot - returns the smallest value of the heap
func QheapRoot(h []int) int {
	return h[0]
}

// Run - implements the solution to the problem
func Run(quick bool, input [][]string) []string {
	heapInsert := SheapInsert
	heapDelete := SheapDelete
	heapRoot := SheapRoot
	if quick {
		heapInsert = QheapInsert
		heapDelete = QheapDelete
		heapRoot = QheapRoot
	}
	n, _ := strconv.Atoi(input[0][0])
	var h []int
	var results []string
	for i := range n {
		arguments := input[i+1]
		operation, _ := strconv.Atoi(arguments[0])
		switch operation {
		case 1:
			value, _ := strconv.Atoi(arguments[1])
			heapInsert(&h, value)
		case 2:
			value, _ := strconv.Atoi(arguments[1])
			heapDelete(&h, value)
		case 3:
			results = append(results, strconv.Itoa(heapRoot(h)))
		}
	}
	return results
}
