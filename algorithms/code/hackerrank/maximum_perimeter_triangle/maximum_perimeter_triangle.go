// Package maximumperimetertriangle implements https://www.hackerrank.com/challenges/maximum-perimeter-triangle
package maximumperimetertriangle

import "sort"

// MaximumPerimeterTriangle - implements the solution to the problem
func MaximumPerimeterTriangle(sticks []int32) []int32 {
	sort.Slice(sticks, func(i, j int) bool { return sticks[j] < sticks[i] })
	for i := 2; i < len(sticks); i++ {
		for j := 0; j < i-1; j++ {
			for k := j + 1; k < i; k++ {
				a := sticks[i]
				b := sticks[k]
				c := sticks[j]
				if a+b > c && a+c > b {
					return []int32{a, b, c}
				}
			}
		}
	}
	return []int32{-1}
}
