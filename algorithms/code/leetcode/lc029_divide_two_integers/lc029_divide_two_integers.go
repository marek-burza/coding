// Package lc029 implements https://leetcode.com/problems/divide-two-integers/
package lc029

import (
	"math"
)

func abs(a int64) int64 {
	if a < 0 {
		a = -a
	}
	return a
}

// Divide divides two integers without using multiplication, division, and mod operator
func Divide(dividend int, divisor int) int {
	if divisor == 0 {
		return math.MaxInt32
	}
	if dividend == 0 {
		return 0
	}
	longDividend := int64(dividend)
	longDivisor := int64(divisor)
	sign := (longDividend / abs(longDividend)) * (longDivisor / abs(longDivisor))
	longDividend = abs(longDividend)
	longDivisor = abs(longDivisor)
	var counter int64
	for longDividend >= longDivisor {
		subtractor := longDivisor
		incrementor := int64(1)
		for longDividend-subtractor-subtractor >= 0 {
			subtractor += subtractor
			incrementor += incrementor
		}
		longDividend -= subtractor
		counter += incrementor
	}
	result := sign * counter
	if result > math.MaxInt32 {
		return math.MaxInt32
	}
	return int(result)
}
