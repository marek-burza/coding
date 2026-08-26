// Package lc090 implements https://leetcode.com/problems/subsets-ii/
// #medium
package lc090

import "sort"

func subsetsInternal(nums []int, offset int, current []int, listed *[][]int) {
	*listed = append(*listed, append([]int{}, current...))
	i := offset
	for i < len(nums) {
		count := 1
		j := i + 1
		for j < len(nums) && nums[j-1] == nums[j] {
			count++
			j++
		}
		for range count {
			current = append(current, nums[i])
			subsetsInternal(nums, i+count, current, listed)
		}
		current = current[:len(current)-count]
		i += count
	}
}

func subsetsWithDup(nums []int) [][]int {
	sort.Ints(nums)
	var listed [][]int
	subsetsInternal(nums, 0, []int{}, &listed)
	return listed
}
