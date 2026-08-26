// Package lc169 implements https://leetcode.com/problems/majority-element/
package lc169

func majorityElement(nums []int) int {
	frequencies := make(map[int]int)
	for _, value := range nums {
		frequencies[value]++
	}
	result := 0 // Instead of -inf
	count := 0  // Instead of -inf
	for value, frequenciesValue := range frequencies {
		other := frequenciesValue
		if count <= other {
			result = value
			count = other
		}
	}
	return result
}
