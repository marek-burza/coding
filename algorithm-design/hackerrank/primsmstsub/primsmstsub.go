package primsmstsub

import "math"

// https://www.hackerrank.com/challenges/primsmstsub

// Heap - Generic heap implementation
type Heap struct {
	size    int
	heap    []interface{}
	compare func(interface{}, interface{}) int
}

func heapChild(index int) int {
	return (index << 1) + 1
}

func heapParent(index int) int {
	return (index - 1) >> 1
}

func heapInit(comparator func(interface{}, interface{}) int) *Heap {
	return &Heap{0, make([]interface{}, 0), comparator}
}

func (heap *Heap) heapDownwards(index int) { // heapify down, percolate down
	swap := heap.heap[index]
	for heapChild(index) < heap.size {
		child := heapChild(index)
		if child+1 < heap.size {
			if heap.compare(heap.heap[child], heap.heap[child+1]) > 0 {
				child++
			}
		}
		if heap.compare(swap, heap.heap[child]) > 0 {
			heap.heap[index] = heap.heap[child]
		} else {
			break
		}
		index = child
	}
	heap.heap[index] = swap
}

func (heap *Heap) heapUpwards(index int) { // heapify up, percolate up
	for index > 0 && 0 > heap.compare(heap.heap[index], heap.heap[heapParent(index)]) {
		parent := heapParent(index)
		swap := heap.heap[parent]
		heap.heap[parent] = heap.heap[index]
		heap.heap[index] = swap
		index = parent
	}
}

func (heap *Heap) heapInsert(value interface{}) {
	heap.heapProtect()
	index := heap.size
	heap.heap[index] = value
	heap.size++
	heap.heapUpwards(index)
}

func (heap *Heap) heapSearch(value interface{}) int {
	for index := 0; index < heap.size; index++ {
		if heap.compare(heap.heap[index], value) == 0 {
			return index
		}
	}
	return -1
}

func (heap *Heap) heapPop() interface{} {
	result := heap.heap[0]
	heap.heapDelete(0)
	return result
}

func (heap *Heap) heapDelete(index int) {
	heap.size--
	heap.heap[index] = heap.heap[heap.size]
	parent := heapParent(index)
	if index == 0 || heap.compare(heap.heap[parent], heap.heap[index]) < 0 {
		heap.heapDownwards(index)
	} else {
		heap.heapUpwards(index)
	}
}

func (heap *Heap) heapProtect() {
	capacity := len(heap.heap)
	if capacity == heap.size {
		heap.heap = append(heap.heap, nil)
	}
}

func (heap *Heap) heapEmpty() bool {
	return heap.size == 0
}

func heapLevel(index int) int {
	return int(math.Floor(math.Log2(float64(index + 1))))
}

// Edge - Weighted graph edge
type Edge struct {
	origin int32
	vertex int32
	weight int32
}

func compareEdges(thisRaw interface{}, otherRaw interface{}) int {
	this := thisRaw.(*Edge)
	other := otherRaw.(*Edge)
	return int((*this).weight - (*other).weight)
}

// Prims - implements the solution to the problem
func Prims(n int32, edges [][]int32, start int32) int32 {
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
	connected := make(map[int32]struct{}, 0)
	exists := struct{}{}
	queue := heapInit(compareEdges)
	total := int32(0)
	for len(connected) < int(n) {
		vertex := start
		for !queue.heapEmpty() {
			edge := queue.heapPop().(*Edge)
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
		adjacentToVertex, _ := adjacency[vertex]
		for _, edge := range adjacentToVertex {
			queue.heapInsert(edge)
		}
	}
	return total
}
