package biggerisgreater

// https://www.hackerrank.com/challenges/bigger-is-greater

import (
	"slices"
)

// BiggerIsGreater - implements the solution to the problem
func BiggerIsGreater(w string) string {
	array := []rune(w)
	length := len(array)
	for i := length - 1; 0 < i; i-- {
		if array[i-1] < array[i] {
			rest := array[i:]
			slices.Sort(rest)
			for j := i; j < length; j++ {
				if array[i-1] < array[j] {
					array[i-1], array[j] = array[j], array[i-1]
					rest := array[i:]
					slices.Sort(rest)
					return string(array)
				}
			}
		}
	}
	return "no answer"
}
