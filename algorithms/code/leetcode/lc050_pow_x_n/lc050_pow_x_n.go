// Package lc050 implements https://leetcode.com/problems/powx-n/
// #medium
package lc050

import "slices"

func myPow(x float64, n int) float64 {
	if n == 0 {
		return 1.0
	}
	count := n
	if n < 0 {
		count = -n
	}
	result := x
	power := 1
	var powers []float64
	for (power << 1) <= count {
		powers = append(powers, result)
		result *= result
		power <<= 1
	}
	previous := power >> 1
	for _, value := range slices.Backward(powers) {
		repeat := (count - power) / previous
		for range repeat {
			result *= value
		}
		power += repeat * previous
		previous >>= 1
	}
	if n < 0 {
		result = 1.0 / result
	}
	return result
}
