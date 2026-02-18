// Package lc012 implements https://leetcode.com/problems/integer-to-roman/
package lc012

import (
	"strings"
)

func intToRoman(num int) string {
	digits := []string{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}
	weights := []int{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}
	var roman strings.Builder
	for i := range digits {
		multiple := num / weights[i]
		for range multiple {
			roman.WriteString(digits[i])
		}
		num -= multiple * weights[i]
	}
	return roman.String()
}
