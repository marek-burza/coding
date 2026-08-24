// Package lc048 implements https://leetcode.com/problems/rotate-image/
// #medium
package lc048

func rotate(matrix [][]int) {
	row := 0
	for row < (len(matrix)/2)+(len(matrix)&1) {
		column := row
		for column < len(matrix)-1-row {
			exchange := matrix[row][column]
			matrix[row][column] = matrix[len(matrix)-1-column][row]
			value := matrix[len(matrix)-1-row][len(matrix)-1-column]
			matrix[len(matrix)-1-column][row] = value
			value = matrix[column][len(matrix)-1-row]
			matrix[len(matrix)-1-row][len(matrix)-1-column] = value
			matrix[column][len(matrix)-1-row] = exchange
			column++
		}
		row++
	}
}
