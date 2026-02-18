// https://www.hackerrank.com/challenges/primsmstsub
package primsmstsub

import "container/heap"

type GenericHeap struct {
	items   []any
	compare func(any, any) int
}

// Len - Size of the generic heap
func (gh GenericHeap) Len() int {
	return len(gh.items)
}

func (gh GenericHeap) Less(i, j int) bool {
	return gh.compare(gh.items[i], gh.items[j]) < 0
}

func (gh GenericHeap) Swap(i, j int) {
	gh.items[i], gh.items[j] = gh.items[j], gh.items[i]
}

func (gh *GenericHeap) Push(item any) {
	gh.items = append(gh.items, item)
}

func (gh *GenericHeap) Pop() any {
	old := gh.items
	n := len(old)
	item := old[n-1]
	old[n-1] = nil // avoid memory leak
	gh.items = old[0 : n-1]
	return item
}

func heapInit(comparator func(any, any) int) *GenericHeap {
	genericHeap := &GenericHeap{make([]any, 0), comparator}
	heap.Init(genericHeap)
	return genericHeap
}

func (gh *GenericHeap) heapInsert(value any) {
	heap.Push(gh, value)
}

func (gh *GenericHeap) heapPop() any {
	return heap.Pop(gh)
}

func (gh *GenericHeap) heapEmpty() bool {
	return gh.Len() == 0
}

type Edge struct {
	origin int32
	vertex int32
	weight int32
}

func compareEdges(thisRaw any, otherRaw any) int {
	this := thisRaw.(*Edge)
	other := otherRaw.(*Edge)
	return int(this.weight - other.weight)
}

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
		//     break
		// }
		connected[vertex] = exists
		for _, edge := range adjacency[vertex] {
			queue.heapInsert(edge)
		}
	}
	return total
}
