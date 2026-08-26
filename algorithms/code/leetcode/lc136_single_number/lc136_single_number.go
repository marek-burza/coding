// Package lc136 implements https://leetcode.com/problems/single-number/
package lc136

func singleNumber(nums []int) int {
	result := 0
	for _, value := range nums {
		result ^= value
	}
	return result
}
