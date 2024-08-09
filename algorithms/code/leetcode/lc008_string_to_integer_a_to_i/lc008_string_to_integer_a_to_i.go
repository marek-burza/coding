package lc008

// https://leetcode.com/problems/string-to-integer-atoi/

import (
	"math"
	"unicode"
)

func myAtoi(s string) int {
	var result int64 = 0
	var sign int64 = 1
	index := 0
	for index < len(s) && unicode.IsSpace(rune(s[index])) {
		index++
	}
	if index < len(s) {
		if s[index] == '-' {
			sign = -1
			index++
		} else if s[index] == '+' {
			sign = 1
			index++
		}
	}
	for index < len(s) && unicode.IsDigit(rune(s[index])) {
		result = result*10 + int64(s[index]-'0')
		if result > math.MaxInt32 {
			break
		}
		index++
	}
	result *= sign
	if result < math.MinInt32 {
		return math.MinInt32
	} else if result > math.MaxInt32 {
		return math.MaxInt32
	}
	return int(result)
}
