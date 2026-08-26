// Package lc118 implements https://leetcode.com/problems/pascals-triangle/
package lc118

func generate(numRows int) [][]int {
	if numRows < 0 {
		return nil
	}
	var triangle [][]int
	for i := range numRows {
		var row []int
		row = append(row, 1)
		if i > 0 {
			above := triangle[i-1]
			for j := range i - 1 {
				row = append(row, above[j]+above[j+1])
			}
			row = append(row, 1)
		}
		triangle = append(triangle, row)
	}
	return triangle
}
