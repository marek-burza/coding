// Package lc338 implements https://leetcode.com/problems/counting-bits/
package lc338

func countBits(n int) []int {
	result := make([]int, n+1)
	threshold := 1
	for i := range result {
		if threshold<<1 <= i {
			threshold <<= 1
		}
		if i == 0 {
			result[0] = 0
		} else {
			result[i] = 1 + result[i-threshold]
		}
	}
	return result
}
