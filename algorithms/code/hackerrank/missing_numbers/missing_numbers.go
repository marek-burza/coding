package missingnumbers

// https://www.hackerrank.com/challenges/missing-numbers

import (
	"sort"
)

// MissingNumbers - implements the solution to the problem
func MissingNumbers(arr []int32, brr []int32) []int32 {
	exists := struct{}{}
	missing := make(map[int32]struct{})
	sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
	sort.Slice(brr, func(i, j int) bool { return brr[i] < brr[j] })
	n := len(arr)
	m := len(brr)
	i := 0
	for j := 0; j < m; j++ {
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
	sort.Slice(keys, func(i, j int) bool { return keys[i] < keys[j] })
	return keys
}
