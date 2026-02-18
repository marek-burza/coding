// Package encryption implements https://www.hackerrank.com/challenges/encryption
package encryption

import (
	"math"
)

// Encryption - implements the solution to the problem
func Encryption(plain string) string {
	p := []rune(plain)
	length := len(p)
	floor := int(math.Floor(math.Sqrt(float64(length))))
	ceil := int(math.Ceil(math.Sqrt(float64(length))))
	rows := -1
	cols := -1
	for c := ceil; c >= floor; c-- {
		extend := 0
		if length%c > 0 {
			extend = 1
		}
		r := (length / c) + extend
		if r*c >= length {
			rows = r
			cols = c
			break
		}
	}
	result := make([]rune, 0)
	for c := 0; c < cols; c++ {
		if c != 0 {
			result = append(result, ' ')
		}
		for r := 0; r < rows; r++ {
			index := r*cols + c
			if index < length {
				result = append(result, p[index])
			}
		}
	}
	return string(result)
}
