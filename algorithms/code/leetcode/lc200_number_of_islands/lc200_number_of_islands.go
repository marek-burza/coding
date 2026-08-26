// Package lc200 implements https://leetcode.com/problems/number-of-islands/
// #medium
package lc200

var deltas = [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}

func land(grid [][]string, x int, y int) bool {
	if x < 0 || len(grid) <= x {
		return false
	}
	if y < 0 || len(grid[x]) <= y {
		return false
	}
	return grid[x][y] == "1"
}

func traverse(grid [][]string, x int, y int) bool {
	var items [][2]int
	items = append(items, [2]int{x, y})
	found := false
	for len(items) > 0 {
		x, y = items[0][0], items[0][1]
		items = items[1:]
		check := land(grid, x, y)
		if check {
			found = true
			grid[x][y] = "0"
			for _, delta := range deltas {
				xx := x + delta[0]
				yy := y + delta[1]
				items = append(items, [2]int{xx, yy})
			}
		}
	}
	return found
}

func numIslands(grid [][]string) int {
	if len(grid) == 0 {
		return 0
	}
	count := 0
	for x, gridX := range grid {
		for y := range gridX {
			if traverse(grid, x, y) {
				count++
			}
		}
	}
	return count
}
