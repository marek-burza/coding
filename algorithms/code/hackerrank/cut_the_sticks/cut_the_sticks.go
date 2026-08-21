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
	for i, a := range slices.Backward(arr) {
		count++
		if i == 0 || a != arr[i-1] {
			cuts = append(cuts, count)
		}
	}
	slices.Reverse(cuts)
	return cuts
}
