// Package lc137 implements https://leetcode.com/problems/single-number-ii/
// #medium #google
package lc137

func singleNumber(nums []int) int {
	counters := make([]int, 32)
	for _, num := range nums {
		for i := range counters {
			counters[i] += num & 1
			num >>= 1
		}
	}
	result := 0
	mask := 1
	for i := range counters {
		if counters[i]%3 != 0 {
			result |= mask
		}
		mask <<= 1
	}
	return result
}
