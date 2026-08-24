// Package lc073 implements https://leetcode.com/problems/set-matrix-zeroes/
// #medium
package lc073

func setZeroes(matrix [][]int) {
	row0 := false
	for row := range matrix {
		if matrix[row][0] == 0 {
			row0 = true
		}
	}
	col0 := false
	for col := range matrix[0] {
		if matrix[0][col] == 0 {
			col0 = true
		}
	}
	for row := 1; row < len(matrix); row++ {
		for col := 1; col < len(matrix[row]); col++ {
			if matrix[row][col] == 0 {
				matrix[row][0] = 0
				matrix[0][col] = 0
			}
		}
	}
	for row := 1; row < len(matrix); row++ {
		for col := 1; col < len(matrix[row]); col++ {
			if matrix[row][0] == 0 || matrix[0][col] == 0 {
				matrix[row][col] = 0
			}
		}
	}
	if row0 {
		for row := range matrix {
			matrix[row][0] = 0
		}
	}
	if col0 {
		for col := range matrix[0] {
			matrix[0][col] = 0
		}
	}
}
