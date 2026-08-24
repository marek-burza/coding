// Package lc038 implements https://leetcode.com/problems/count-and-say/
// #medium
package lc038

import (
	"strconv"
	"strings"
)

func countAndSay(n int) string {
	if n < 1 {
		return ""
	}
	result := "1"
	for n > 1 {
		var current strings.Builder
		check := byte('0')
		count := 0
		for i := range len(result) {
			character := result[i]
			if check != character {
				if count > 0 {
					current.WriteString(strconv.Itoa(count))
					current.WriteByte(check)
				}
				count = 1
				check = character
			} else {
				count++
			}
		}
		current.WriteString(strconv.Itoa(count))
		current.WriteByte(check)
		n--
		result = current.String()
	}
	return result
}
