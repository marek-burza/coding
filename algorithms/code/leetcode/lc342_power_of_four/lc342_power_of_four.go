// Package lc342 implements https://leetcode.com/problems/power-of-four/
package lc342

import "math"

func isPowerOfFour(num int) bool {
	if num <= 0 {
		return false
	}
	value := math.Log(float64(num)) / math.Log(4)
	return value == math.Floor(value)
}
