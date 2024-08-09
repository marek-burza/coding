package lc011

// https://leetcode.com/problems/container-with-most-water/

import (
	"math"
)

func maxArea(height []int) int {
	max := 0
	low := 0
	high := len(height) - 1
	for low < high {
		top := int(math.Min(float64(height[low]), float64(height[high])))
		max = int(math.Max(float64(max), float64(top*(high-low))))
		if height[low] <= height[high] {
			low++
		} else {
			high--
		}
	}
	return max
}
