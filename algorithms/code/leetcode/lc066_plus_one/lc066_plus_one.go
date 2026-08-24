// Package lc066 implements https://leetcode.com/problems/plus-one/
package lc066

func plusOne(digits []int) []int {
	carry := 1
	for i := len(digits) - 1; i >= 0; i-- {
		digits[i] += carry
		carry = digits[i] / 10
		digits[i] %= 10
	}
	if carry > 0 {
		bigger := append([]int{carry}, digits...)
		return bigger
	}
	return digits
}
