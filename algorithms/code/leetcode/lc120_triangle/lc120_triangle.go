// Package lc120 implements https://leetcode.com/problems/triangle/
// #medium
package lc120

import "slices"

func minimumTotal(triangle [][]int) int {
	if len(triangle) == 0 || len(triangle[0]) == 0 {
		return 0
	}
	height := len(triangle)
	sums := make([]int, len(triangle[height-1]))
	sums[0] = triangle[0][0]
	for i := 1; i < height; i++ {
		line := triangle[i]
		n := len(line)
		for j := n - 1; j >= 0; j-- {
			var values []int
			if j != 0 {
				values = append(values, sums[j-1])
			}
			if j != n-1 {
				values = append(values, sums[j])
			}
			minimum := slices.Min(values)
			sums[j] = minimum + line[j]
		}
	}
	minimum := sums[0]
	for i := range sums {
		if sums[i] < minimum {
			minimum = sums[i]
		}
	}
	return minimum
}
