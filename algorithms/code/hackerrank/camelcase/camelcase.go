// Package camelcase implements https://www.hackerrank.com/challenges/camelcase
package camelcase

import (
	"unicode"
)

// Camelcase - implements the solution to the problem
func Camelcase(s string) int32 {
	var count int32 = 1
	for _, character := range s {
		if unicode.IsUpper(character) {
			count++
		}
	}
	return count
}
