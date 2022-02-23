package dijkstrashortreach

import "container/heap"

// https://www.hackerrank.com/challenges/dijkstrashortreach

// GenericHeap - Generic heap implementation
type GenericHeap struct {
	items   []interface{}
	compare func(interface{}, interface{}) int
}

// Len - Size of the generic heap
func (gh GenericHeap) Len() int {
	return len(gh.items)
}

// Less - Implements the generic heap comparator
func (gh GenericHeap) Less(i, j int) bool {
	return gh.compare(gh.items[i], gh.items[j]) < 0
}

// Swap - Implements the generic heap swap
func (gh GenericHeap) Swap(i, j int) {
	gh.items[i], gh.items[j] = gh.items[j], gh.items[i]
}

// Push - Implements the generic heap push operation
func (gh *GenericHeap) Push(item interface{}) {
	gh.items = append(gh.items, item)
}

// Pop - Implements the generic heap pop operation
func (gh *GenericHeap) Pop() interface{} {
	old := gh.items
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	gh.items = old[0 : n-1]
	return item
}

func heapInit(comparator func(interface{}, interface{}) int) *GenericHeap {
	genericHeap := &GenericHeap{make([]interface{}, 0), comparator}
	heap.Init(genericHeap)
	return genericHeap
}

func (gh *GenericHeap) heapInsert(value interface{}) {
	heap.Push(gh, value)
}

func (gh *GenericHeap) heapPop() interface{} {
	return heap.Pop(gh)
}

func (gh *GenericHeap) heapEmpty() bool {
	return gh.Len() == 0
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

func compareVertices(thisRaw interface{}, otherRaw interface{}) int {
	this := thisRaw.(*Vertex)
	other := otherRaw.(*Vertex)
	return int(this.distance - other.distance)
}

// ShortestReach - implements the solution to the problem
func ShortestReach(n int32, edges [][]int32, s int32) []int32 {
	maximum := int64(^uint64(0) >> 1)
	adjacency := make(map[int32][]*Edge)
	for _, edge := range edges {
		_, edge0InAdjacency := adjacency[edge[0]]
		if !edge0InAdjacency {
			adjacency[edge[0]] = make([]*Edge, 0)
		}
		_, edge1InAdjacency := adjacency[edge[1]]
		if !edge1InAdjacency {
			adjacency[edge[1]] = make([]*Edge, 0)
		}
		edge01 := &Edge{edge[0], edge[1], edge[2]}
		adjacency[edge[0]] = append(adjacency[edge[0]], edge01)
		edge10 := &Edge{edge[1], edge[0], edge[2]}
		adjacency[edge[1]] = append(adjacency[edge[1]], edge10)
	}
	vertices := make([]*Vertex, n+1)
	for i, adjacent := range adjacency {
		vertices[i] = &Vertex{maximum, adjacent}
	}
	unvisited := heapInit(compareVertices)
	unvisited.heapInsert(vertices[s])
	vertices[s].distance = 0
	for !unvisited.heapEmpty() {
		vertex := unvisited.heapPop().(*Vertex)
		for _, edge := range vertex.edges {
			other := vertices[edge.vertex]
			candidate := vertex.distance + int64(edge.weight)
			if candidate < other.distance {
				other.distance = candidate
				unvisited.heapInsert(other)
			}
		}
	}
	distances := make([]int32, n-1)
	index := 0
	for i := 0; i < int(n)+1; i++ {
		if i != 0 && i != int(s) {
			distances[index] = int32(vertices[i].distance)
			index++
		}
	}
	return distances
}
