// Package cutthesticks implements https://www.hackerrank.com/challenges/cut-the-sticks
package cutthesticks

import (
	"slices"
)

// CutTheSticks - implements the solution to the problem
func CutTheSticks(arr []int32) []int32 {
	slices.Sort(arr)
	cuts := make([]int32, 0)
	count := int32(0)
	for i := len(arr) - 1; i >= 0; i-- {
		count++
		if i == 0 || arr[i] != arr[i-1] {
			cuts = append(cuts, count)
		}
	}
	slices.Reverse(cuts)
	return cuts
}
