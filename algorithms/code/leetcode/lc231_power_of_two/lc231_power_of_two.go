// Package lc231 implements https://leetcode.com/problems/power-of-two/
// #google
package lc231

func isPowerOfTwo(n int) bool {
	if n <= 0 {
		return false
	}
	count := 0
	mask := 1
	for mask != 0 {
		if (n & mask) != 0 {
			count++
		}
		mask = (mask << 1) & 0xFFFFFFFF
	}
	return count == 1
}
