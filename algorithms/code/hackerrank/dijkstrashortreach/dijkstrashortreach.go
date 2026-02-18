// Package dijkstrashortreach implements https://www.hackerrank.com/challenges/dijkstrashortreach
package dijkstrashortreach

import (
	"container/heap"
	"math"
)

// VertexHeap - Vertex heap implementation
type VertexHeap []*Vertex

// Len - Size of the generic heap
func (vh VertexHeap) Len() int {
	return len(vh)
}

// Less - Implements the generic heap comparator
func (vh VertexHeap) Less(i, j int) bool {
	return vh[i].distance < vh[j].distance
}

// Swap - Implements the generic heap swap
func (vh VertexHeap) Swap(i, j int) {
	vh[i], vh[j] = vh[j], vh[i]
}

// Push - Implements the generic heap push operation
func (vh *VertexHeap) Push(item any) {
	*vh = append(*vh, item.(*Vertex))
}

// Pop - Implements the generic heap pop operation
func (vh *VertexHeap) Pop() any {
	old := *vh
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	*vh = old[:n-1]
	return item
}

// Edge - Weighted graph edge
type Edge struct {
	origin int32
	vertex int32
	weight int32
}

// Vertex - Graph vertex
type Vertex struct {
	distance int64
	edges    []*Edge
}

// ShortestReach - implements the solution to the problem
func ShortestReach(n int32, edges [][]int32, s int32) []int32 {
	adjacency := make(map[int32][]*Edge)
	for _, edge := range edges {
		edge01 := &Edge{edge[0], edge[1], edge[2]}
		adjacency[edge[0]] = append(adjacency[edge[0]], edge01)
		edge10 := &Edge{edge[1], edge[0], edge[2]}
		adjacency[edge[1]] = append(adjacency[edge[1]], edge10)
	}
	vertices := make([]*Vertex, n+1)
	for i, adjacent := range adjacency {
		vertices[i] = &Vertex{math.MaxInt64, adjacent}
	}
	vertices[s].distance = 0
	unvisited := &VertexHeap{}
	heap.Init(unvisited)
	heap.Push(unvisited, vertices[s])
	for unvisited.Len() > 0 {
		vertex := heap.Pop(unvisited).(*Vertex)
		for _, edge := range vertex.edges {
			other := vertices[edge.vertex]
			candidate := vertex.distance + int64(edge.weight)
			if candidate < other.distance {
				other.distance = candidate
				heap.Push(unvisited, other)
			}
		}
	}
	distances := make([]int32, 0, n-1)
	for i := int32(1); i <= n; i++ {
		if i != s {
			distances = append(distances, int32(vertices[i].distance))
		}
	}
	return distances
}
