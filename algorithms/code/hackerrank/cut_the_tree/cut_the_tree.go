// Package cutthetree implements https://www.hackerrank.com/challenges/cut-the-tree
package cutthetree

var exists = struct{}{}

type vertex struct {
	index    int32
	value    int32
	adjacent *[]vertex
}

func abs(value int32) int32 {
	if value < 0 {
		value = -value
	}
	return value
}

func buildGraph(data []int32, edges [][]int32) map[int32]vertex {
	mapped := make(map[int32]vertex)
	for i := range data {
		index := int32(i + 1)
		other := make([]vertex, 0)
		mapped[index] = vertex{index, data[i], &other}
	}
	for _, edge := range edges {
		*(mapped[edge[0]].adjacent) = append(*(mapped[edge[0]].adjacent), mapped[edge[1]])
		*(mapped[edge[1]].adjacent) = append(*(mapped[edge[0]].adjacent), mapped[edge[0]])
	}
	return mapped
}

func maximumEdge(graph map[int32]vertex, v int32, total int32, minimum *int32, seen *map[int32]struct{}) int32 {
	_, seenContainsV := (*seen)[v]
	if seenContainsV {
		return 0
	}
	sum := graph[v].value
	(*seen)[v] = exists
	for _, other := range *graph[v].adjacent {
		partial := maximumEdge(graph, other.index, total, minimum, seen)
		candidate := abs(total - 2*partial)
		if candidate < *minimum {
			*minimum = candidate
		}
		sum += partial
	}
	return sum
}

// CutTheTree - implements the solution to the problem
func CutTheTree(data []int32, edges [][]int32) int32 {
	graph := buildGraph(data, edges)
	total := int32(0)
	for _, value := range data {
		total += value
	}
	minimum := int32(^uint32(0) >> 1)
	seen := make(map[int32]struct{})
	maximumEdge(graph, 1, total, &minimum, &seen)
	return minimum
}
