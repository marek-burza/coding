// Package lc279 implements https://leetcode.com/problems/perfect-squares/
package lc279

import "slices"

func numSquares(n int) int {
	lut := slices.Repeat([]int{2 * n}, n+1) // Instead of inf
	lut[0] = 0
	i := 1
	ii := 1
	for ii <= n {
		j := ii
		for j < len(lut) {
			lut[j] = min(lut[j], lut[j-ii]+1)
			j++
		}
		i++
		ii = i * i
	}
	return lut[n]
}
