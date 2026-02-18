// Package missingnumbers implements https://www.hackerrank.com/challenges/missing-numbers
package missingnumbers

import (
	"slices"
)

// MissingNumbers - implements the solution to the problem
func MissingNumbers(arr []int32, brr []int32) []int32 {
	exists := struct{}{}
	missing := make(map[int32]struct{})
	slices.Sort(arr)
	slices.Sort(brr)
	n := len(arr)
	m := len(brr)
	i := 0
	for j := range m {
		if i < n {
			if arr[i] == brr[j] {
				i++
			} else {
				missing[brr[j]] = exists
			}
		} else {
			missing[brr[j]] = exists
		}
	}
	keys := make([]int32, 0, len(missing))
	for key := range missing {
		keys = append(keys, key)
	}
	slices.Sort(keys)
	return keys
}
