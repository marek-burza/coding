// Package staircase implements https://www.hackerrank.com/challenges/staircase
package staircase

import "strings"

// Staircase - implements the solution to the problem
func Staircase(n int32) []string {
	result := make([]string, 0)
	for index := 0; index < int(n); index++ {
		var line strings.Builder
		for i := 0; i < int(n); i++ {
			if i < int(n)-1-index {
				line.WriteString(" ")
			} else {
				line.WriteString("#")
			}
		}
		result = append(result, line.String())
	}
	return result
}
