// Package lc168 implements https://leetcode.com/problems/excel-sheet-column-title/
package lc168

import "slices"

func convertToTitle(columnNumber int) string {
	n := columnNumber
	var buffer []byte
	condition := true
	for condition {
		n--
		digit := byte('A' + (n % 26))
		buffer = append(buffer, digit)
		n -= n % 26
		n /= 26
		condition = n > 0
	}
	slices.Reverse(buffer)
	return string(buffer)
}
