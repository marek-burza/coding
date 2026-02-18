// Package primsmstsub implements https://www.hackerrank.com/challenges/primsmstsub
package primsmstsub

import "container/heap"

// EdgeHeap - Edge heap implementation
type EdgeHeap []*Edge

// Len - Size of the generic heap
func (eh EdgeHeap) Len() int {
	return len(eh)
}

// Less - Implements the generic heap comparator
func (eh EdgeHeap) Less(i, j int) bool {
	return eh[i].weight < eh[j].weight
}

// Swap - Implements the generic heap swap
func (eh EdgeHeap) Swap(i, j int) {
	eh[i], eh[j] = eh[j], eh[i]
}

// Push - Implements the generic heap push operation
func (eh *EdgeHeap) Push(item any) {
	*eh = append(*eh, item.(*Edge))
}

// Pop - Implements the generic heap pop operation
func (eh *EdgeHeap) Pop() any {
	old := *eh
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	*eh = old[:n-1]
	return item
}

// Edge - Weighted graph edge
type Edge struct {
	origin int32
	vertex int32
	weight int32
}

// Prims - implements the solution to the problem
func Prims(n int32, edges [][]int32, start int32) int32 {
	adjacency := make([][]*Edge, n+1)
	for _, edge := range edges {
		edge01 := &Edge{edge[0], edge[1], edge[2]}
		adjacency[edge[0]] = append(adjacency[edge[0]], edge01)
		edge10 := &Edge{edge[1], edge[0], edge[2]}
		adjacency[edge[1]] = append(adjacency[edge[1]], edge10)
	}
	connected := make(map[int32]struct{}, 0)
	exists := struct{}{}
	queue := &EdgeHeap{}
	total := int32(0)
	for len(connected) < int(n) {
		vertex := start
		for queue.Len() > 0 {
			edge := heap.Pop(queue).(*Edge)
			_, edgeVertexInConnected := connected[edge.vertex]
			if !edgeVertexInConnected {
				vertex = edge.vertex
				total += edge.weight
				break
			}
		}
		// _, startInConnected := connected[start]
		// if vertex == start && startInConnected {
		// 	break
		// }
		connected[vertex] = exists
		for _, edge := range adjacency[vertex] {
			heap.Push(queue, edge)
		}
	}
	return total
}
