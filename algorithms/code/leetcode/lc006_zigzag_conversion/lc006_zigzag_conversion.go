package lc006

// https://leetcode.com/problems/zigzag-conversion/

import (
	"strings"
)

func convert(s string, numRows int) string {
	if s == "" || numRows < 1 {
		return ""
	}
	// if numRows == 1 {
	// 	return s
	// }
	var buffer strings.Builder
	n := len(s)
	scan := (numRows - 1) * 2
	if scan == 0 {
		scan = 1
	}
	for row := 0; row < numRows; row++ {
		for i := row; i < n; i += scan {
			buffer.WriteByte(s[i])
			if row > 0 && row < numRows-1 {
				offset := (numRows - 1 - row) * 2
				if i+offset >= n {
					break
				}
				buffer.WriteByte(s[i+offset])
			}
		}
	}
	return buffer.String()
}
