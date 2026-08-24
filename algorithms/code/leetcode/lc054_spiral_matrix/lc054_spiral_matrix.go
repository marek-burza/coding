// Package lc054 implements https://leetcode.com/problems/spiral-matrix/
// #medium
package lc054

var deltas = [][]int{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}

func spiralOrder(matrix [][]int) []int {
	var listed []int
	if len(matrix) == 0 {
		return listed
	}
	index := 0
	top := 0
	bottom := len(matrix) - 1
	left := 0
	right := len(matrix[0]) - 1
	x := 0
	y := 0
	for top <= bottom && left <= right {
		if x > right {
			index = 1
			top++
			y = top
			x--
			continue
		}
		if y > bottom {
			index = 2
			right--
			x = right
			y--
			continue
		}
		if x < left {
			index = 3
			bottom--
			y = bottom
			x++
			continue
		}
		if y < top {
			index = 0
			left++
			x = left
			y++
			continue
		}
		listed = append(listed, matrix[y][x])
		x += deltas[index][0]
		y += deltas[index][1]
	}
	return listed
}
