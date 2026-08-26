// Package lc064 implements https://leetcode.com/problems/minimum-path-sum/
// #medium
package lc064

import "slices"

func minPathSum(grid [][]int) int {
	visited := make([][]bool, len(grid))
	for i := range visited {
		visited[i] = make([]bool, len(grid[0]))
	}
	total := 0 // Instead of inf
	for _, line := range grid {
		for _, value := range line {
			total += value
		}
	}
	sums := make([][]int, len(grid))
	for i := range sums {
		sums[i] = slices.Repeat([]int{total}, len(grid[0]))
	}
	sums[0][0] = grid[0][0]
	var queue [][]int
	queue = append(queue, []int{0, 0})
	for len(queue) > 0 {
		at := queue[0]
		queue = queue[1:]
		if !visited[at[0]][at[1]] {
			visited[at[0]][at[1]] = true
			if at[0]+1 < len(grid) {
				right := []int{at[0] + 1, at[1]}
				queue = append(queue, right)
				summed := sums[at[0]][at[1]] + grid[right[0]][right[1]]
				if summed < sums[right[0]][right[1]] {
					sums[right[0]][right[1]] = summed
				}
			}
			if at[1]+1 < len(grid[0]) {
				down := []int{at[0], at[1] + 1}
				queue = append(queue, down)
				summed := sums[at[0]][at[1]] + grid[down[0]][down[1]]
				// if summed < sums[down[0]][down[1]] {
				sums[down[0]][down[1]] = summed
			}
		}
	}
	return sums[len(sums)-1][len(sums[len(sums)-1])-1]
}
