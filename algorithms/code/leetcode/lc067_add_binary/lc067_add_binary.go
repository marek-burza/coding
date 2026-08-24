// Package lc067 implements https://leetcode.com/problems/add-binary/
package lc067

import (
	"strings"
)

func reversed(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func addBinary(a string, b string) string {
	ar := reversed(a)
	br := reversed(b)
	var result strings.Builder
	carry := 0
	i := 0
	for i < len(ar) || i < len(br) {
		summed := carry
		if i < len(ar) {
			summed += int(ar[i] - '0')
		}
		if i < len(br) {
			summed += int(br[i] - '0')
		}
		carry = summed >> 1
		if (summed & 1) == 0 {
			result.WriteString("0")
		} else {
			result.WriteString("1")
		}
		i++
	}
	if carry == 1 {
		result.WriteString("1")
	}
	return reversed(result.String())
}
