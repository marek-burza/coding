// Package lc078 implements https://leetcode.com/problems/subsets/
// #medium
package lc078

import (
	"sort"
)

func subsetsInternal(nums []int, offset int, current []int, listed *[][]int) {
	*listed = append(*listed, append([]int{}, current...))
	for i := offset; i < len(nums); i++ {
		current = append(current, nums[i])
		subsetsInternal(nums, i+1, current, listed)
		current = current[:len(current)-1]
	}
}

func subsets(nums []int) [][]int {
	sort.Ints(nums)
	var listed [][]int
	subsetsInternal(nums, 0, []int{}, &listed)
	return listed
}
