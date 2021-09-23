package bfsshortreach

// https://www.hackerrank.com/challenges/bfsshortreach

type entry struct {
	vertex   int32
	distance int32
}

// Bfs - implements the solution to the problem
func Bfs(n int32, m int32, edges [][]int32, s int32) []int32 {
	adjacency := make([][]bool, n)
	for i := 0; i < int(n); i++ {
		adjacency[i] = make([]bool, n)
		for j := 0; j < int(n); j++ {
			adjacency[i][j] = false
		}
	}
	for _, edge := range edges {
		adjacency[edge[0]-1][edge[1]-1] = true
		adjacency[edge[1]-1][edge[0]-1] = true
	}
	queue := make([]entry, 0)
	queue = append(queue, entry{s - 1, 0})
	distances := make([]int32, n)
	for i := 0; i < int(n); i++ {
		distances[i] = -1
	}
	for len(queue) > 0 {
		item := queue[0]
		queue = queue[1:]
		if distances[item.vertex] == -1 {
			distances[item.vertex] = item.distance
			for i := 0; i < int(n); i++ {
				if adjacency[int(item.vertex)][i] {
					queue = append(queue, entry{int32(i), item.distance + 6})
				}
			}
		}
	}
	result := make([]int32, n-1)
	index := 0
	for i := 0; i < int(n); i++ {
		if i != int(s-1) {
			result[index] = distances[i]
			index++
		}
	}
	return result
}
