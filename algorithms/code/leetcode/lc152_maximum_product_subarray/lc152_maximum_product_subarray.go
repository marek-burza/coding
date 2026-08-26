// Package lc152 implements https://leetcode.com/problems/maximum-product-subarray/
// #medium
package lc152

import "slices"

func maxProduct(nums []int) int {
	maximum := slices.Min(nums) - 1 // Instead of -inf
	cmin := 0
	cmax := 0
	for _, num := range nums {
		tmin := num
		tmax := num
		if cmin != 0 {
			tmin *= cmin
		}
		if cmax != 0 {
			tmax *= cmax
		}
		if tmin < tmax {
			cmin = tmin
			cmax = tmax
		} else {
			cmin = tmax
			cmax = tmin
		}
		cmin = min(num, cmin)
		cmax = max(num, cmax)
		maximum = max(maximum, cmax)
	}
	return maximum
}
