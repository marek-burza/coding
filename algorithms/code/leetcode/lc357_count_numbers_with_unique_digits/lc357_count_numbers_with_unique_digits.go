// Package lc357 implements https://leetcode.com/problems/count-numbers-with-unique-digits/
// #medium
package lc357

import "strings"

func count(prefix string, n int) int {
	if len(prefix) == n {
		return 1
	}
	summed := 1
	digits := []string{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}
	first := 0
	if len(prefix) == 0 {
		first = 1
	}
	for i := first; i < len(digits); i++ {
		if !strings.Contains(prefix, digits[i]) {
			summed += count(prefix+digits[i], n)
		}
	}
	return summed
}

func countNumbersWithUniqueDigits(n int) int {
	return count("", n)
}
