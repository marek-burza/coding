// Package lc207 implements https://leetcode.com/problems/course-schedule/
// #medium
package lc207

type annotatedNode struct {
	node      int
	ancestors map[int]struct{}
}

func dfs(graph map[int]map[int]struct{}, node int, visited map[int]struct{}) bool {
	queue := []annotatedNode{{node, make(map[int]struct{})}}
	for len(queue) > 0 {
		node, ancestors := queue[0].node, queue[0].ancestors
		queue = queue[1:]
		if _, found := ancestors[node]; found {
			return false
		}
		if _, found := visited[node]; !found {
			visited[node] = struct{}{}
			for other := range graph[node] {
				extended := make(map[int]struct{}, len(ancestors)+1)
				for ancestor := range ancestors {
					extended[ancestor] = struct{}{}
				}
				extended[node] = struct{}{}
				queue = append(queue, annotatedNode{other, extended})
			}
		}
	}
	return true
}

func canFinish(_ int, prerequisites [][]int) bool {
	graph := make(map[int]map[int]struct{})
	for _, prerequisite := range prerequisites {
		aSet, found := graph[prerequisite[1]]
		if !found {
			aSet = make(map[int]struct{})
			graph[prerequisite[1]] = aSet
		}
		aSet[prerequisite[0]] = struct{}{}
	}
	visited := make(map[int]struct{})
	for start := range graph {
		if !dfs(graph, start, visited) {
			return false
		}
	}
	return true
}
