// Package lc081 implements https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// #medium
package lc081

import "sort"

func binarySearch(array []int, begin int, end int, value int) int {
	index := begin + sort.SearchInts(array[begin:end], value)
	if index != len(array) && array[index] == value {
		return index
	}
	return -1
}

func search(nums []int, target int) bool {
	for i := 1; i < len(nums); i++ {
		if nums[i-1] > nums[i] {
			ante := binarySearch(nums, 0, i, target) >= 0
			post := binarySearch(nums, i, len(nums), target) >= 0
			return ante || post
		}
	}
	return binarySearch(nums, 0, len(nums), target) >= 0
}
