package cavitymap

// https://www.hackerrank.com/challenges/cavity-map

import (
	"fmt"
)

// CavityMap - implements the solution to the problem
func CavityMap(grid []string) []string {
	fmt.Printf("%v\n", grid)
	deltas := [][]int{{-1, 0}, {0, -1}, {1, 0}, {0, 1}}
	n := len(grid)
	cells := make([][]rune, n)
	for i := 0; i < n; i++ {
		cells[i] = []rune(grid[i])
	}
	for i := 1; i < n-1; i++ {
		for j := 1; j < n-1; j++ {
			deeper := true
			for _, delta := range deltas {
				if uint16(cells[i+delta[0]][j+delta[1]]) >= uint16(cells[i][j]) {
					deeper = false
				}
			}
			if deeper {
				cells[i][j] = 'X'
			}
		}
	}
	for i := 0; i < n; i++ {
		grid[i] = string(cells[i])
	}
	return grid
}
