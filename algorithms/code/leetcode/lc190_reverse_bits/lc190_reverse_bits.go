// Package lc190 implements https://leetcode.com/problems/reverse-bits/
package lc190

func reverseBits(n int) int {
	r := 0
	for range 32 {
		r <<= 1
		r |= n & 1
		n >>= 1
	}
	return r
}
