// Package lc070 implements https://leetcode.com/problems/climbing-stairs/
package lc070

func climbStairsInternal(n int, at int, lut []int) int {
	if at+2 == n {
		return 2
	}
	if at+1 == n {
		return 1
	}
	if lut[at] == 0 {
		lut[at] = climbStairsInternal(n, at+1, lut)
		lut[at] += climbStairsInternal(n, at+2, lut)
	}
	return lut[at]
}

func climbStairs(n int) int {
	lut := make([]int, n)
	return climbStairsInternal(n, 0, lut)
}
