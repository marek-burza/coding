// Package lc191 implements https://leetcode.com/problems/number-of-1-bits/
package lc191

func hammingWeight(n int) int {
	count := 0
	for range 32 {
		count += n % 2
		n >>= 1
	}
	return count
}
