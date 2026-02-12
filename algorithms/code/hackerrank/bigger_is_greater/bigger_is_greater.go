package biggerisgreater

// https://www.hackerrank.com/challenges/bigger-is-greater

import (
	"sort"
)

// BiggerIsGreater - implements the solution to the problem
func BiggerIsGreater(w string) string {
	array := []rune(w)
	length := len(array)
	for i := length - 1; 0 < i; i-- {
		if array[i-1] < array[i] {
			rest := array[i:]
			sort.Slice(rest, func(k, l int) bool { return rest[k] < rest[l] })
			for j := i; j < length; j++ {
				if array[i-1] < array[j] {
					array[i-1], array[j] = array[j], array[i-1]
					rest := array[i:]
					sort.Slice(rest, func(k, l int) bool { return rest[k] < rest[l] })
					return string(array)
				}
			}
		}
	}
	return "no answer"
}
