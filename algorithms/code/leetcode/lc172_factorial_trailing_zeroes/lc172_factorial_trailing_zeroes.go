// Package lc172 implements https://leetcode.com/problems/factorial-trailing-zeroes/
// #medium
package lc172

func trailingZeroes(n int) int {
	step := 5
	count := 0
	for step <= n {
		count += n / step
		step *= 5
	}
	return count
}
