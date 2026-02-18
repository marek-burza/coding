// Package lc011 implements https://leetcode.com/problems/container-with-most-water/
package lc011

import (
	"math"
)

func maxArea(height []int) int {
	maximum := 0
	low := 0
	high := len(height) - 1
	for low < high {
		top := int(math.Min(float64(height[low]), float64(height[high])))
		maximum = int(math.Max(float64(maximum), float64(top*(high-low))))
		if height[low] <= height[high] {
			low++
		} else {
			high--
		}
	}
	return maximum
}
