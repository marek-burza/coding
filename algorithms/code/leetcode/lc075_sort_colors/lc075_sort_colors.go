// Package lc075 implements https://leetcode.com/problems/sort-colors/
// #medium
package lc075

func sortColors(nums []int) {
	counters := []int{0, 0, 0}
	for _, value := range nums {
		counters[value]++
	}
	i := 0
	j := 0
	for i < len(counters) {
		k := 0
		for k < counters[i] {
			nums[j] = i
			k++
			j++
		}
		i++
	}
}
