// Package lc053 implements https://leetcode.com/problems/maximum-subarray/
package lc053

import (
	"math"
)

func maxSubArray(nums []int) int {
	summed := 0
	minimum := 0
	maximum := math.Inf(-1)
	i := 0
	for i < len(nums) {
		if summed < minimum {
			minimum = summed
		}
		summed += nums[i]
		delta := summed - minimum
		if float64(delta) > maximum {
			maximum = float64(delta)
		}
		i++
	}
	return int(maximum)
}
