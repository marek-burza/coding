// Package lc371 implements https://leetcode.com/problems/sum-of-two-integers/
// #medium
package lc371

func getSum(a int, b int) int {
	result := 0
	carry := 0
	mask := 1
	for mask != 0 {
		am := a & mask
		bm := b & mask
		result |= am ^ bm ^ carry
		carry = (am & bm) | (bm & carry) | (am & carry)
		carry <<= 1
		mask <<= 1
		mask &= 0xFFFFFFFF
	}
	return result
}
