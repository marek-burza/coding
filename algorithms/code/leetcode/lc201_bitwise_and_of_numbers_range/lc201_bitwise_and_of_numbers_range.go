// Package lc201 implements https://leetcode.com/problems/bitwise-and-of-numbers-range/
// #medium
package lc201

func rangeBitwiseAnd(left int, right int) int {
	result := 0
	power := 1
	mask := 0
	for range 32 {
		if (left&power) != 0 && ((power - (left & mask)) > (right - left)) {
			result |= power
		}
		power <<= 1
		mask = (mask << 1) | 1
	}
	return result
	// Alternative: Zero all bits after the first difference
	// when checking from highest to lowest bit
}
