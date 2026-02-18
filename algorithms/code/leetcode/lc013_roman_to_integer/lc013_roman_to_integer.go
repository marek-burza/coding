// Package lc013 implements https://leetcode.com/problems/roman-to-integer/
package lc013

func romanToInt(s string) int {
	if s == "" {
		return 0
	}
	lut := map[byte]int{
		'I': 1,
		'V': 5,
		'X': 10,
		'L': 50,
		'C': 100,
		'D': 500,
		'M': 1000,
	}
	result := 0
	previous := 0
	for i := len(s) - 1; i >= 0; i-- {
		current := lut[s[i]]
		if current < previous {
			result -= current
		} else {
			result += current
		}
		previous = current
	}

	return result
}
