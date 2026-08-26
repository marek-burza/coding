// Package lc171 implements https://leetcode.com/problems/excel-sheet-column-number/
package lc171

func titleToNumber(columnTitle string) int {
	s := columnTitle
	if len(s) == 0 {
		return -1
	}
	result := 0
	i := 0
	for i < len(s) {
		result *= 26
		result += int(s[i]-'A') + 1
		i++
	}
	return result
}
