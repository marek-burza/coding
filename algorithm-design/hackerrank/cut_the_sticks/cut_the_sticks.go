package cavitymap

// https://www.hackerrank.com/challenges/cut-the-sticks

import (
	"fmt"
	"sort"
)

// CutTheSticks - implements the solution to the problem
func CutTheSticks(arr []int32) []int32 {
	fmt.Printf("%v\n", arr)
	sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
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
