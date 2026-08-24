// Package lc046 implements https://leetcode.com/problems/permutations/
// #medium
package lc046

func permuteInternal(prefix []int, remaining map[int]struct{}, permutations *[][]int) {
	if len(remaining) == 0 {
		*permutations = append(*permutations, append([]int{}, prefix...))
	} else {
		for value := range remaining {
			prefix = append(prefix, value)
			reduced := make(map[int]struct{}, len(remaining))
			for other := range remaining {
				reduced[other] = struct{}{}
			}
			delete(reduced, value)
			permuteInternal(prefix, reduced, permutations)
			prefix = prefix[:len(prefix)-1]
		}
	}
}

func permute(nums []int) [][]int {
	var permutations [][]int
	remaining := make(map[int]struct{}, len(nums))
	for _, num := range nums {
		remaining[num] = struct{}{}
	}
	permuteInternal([]int{}, remaining, &permutations)
	return permutations
}
