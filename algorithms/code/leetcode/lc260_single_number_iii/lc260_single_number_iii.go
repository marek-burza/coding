// Package lc260 implements https://leetcode.com/problems/single-number-iii/
// #medium
package lc260

func singleNumber(nums []int) []int {
	xor := 0
	for _, value := range nums {
		xor ^= value
	}
	mask := xor & ^(xor - 1)
	values := []int{0, 0}
	for _, value := range nums {
		if (value & mask) == 0 {
			values[0] ^= value
		} else {
			values[1] ^= value
		}
	}
	return values
}
