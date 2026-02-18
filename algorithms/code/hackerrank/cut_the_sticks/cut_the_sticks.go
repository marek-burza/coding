// Package cutthesticks implements https://www.hackerrank.com/challenges/cut-the-sticks
package cutthesticks

import (
	"slices"
)

// CutTheSticks - implements the solution to the problem
func CutTheSticks(arr []int32) []int32 {
	slices.Sort(arr)
	cuts := make([]int32, 0)
	n := int32(len(arr))
	i := int32(0)
	for i < n {
		cuts = append(cuts, n-i)
		cut := arr[i]
		for i < n && arr[i]-cut <= 0 {
			i++
		}
	}
	return cuts
}
