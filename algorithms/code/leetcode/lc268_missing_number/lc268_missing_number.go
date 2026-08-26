// Package lc268 implements https://leetcode.com/problems/missing-number/
package lc268

func missingNumber(nums []int) int {
	expected := len(nums) * (len(nums) + 1) / 2
	summed := 0
	for _, value := range nums {
		summed += value
	}
	return expected - summed
}
