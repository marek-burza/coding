// Package lc220 implements https://leetcode.com/problems/contains-duplicate-iii/
// #medium
package lc220

import (
	"slices"
	"sort"
)

func bisectLeft(items []int, value int) int {
	return sort.SearchInts(items, value)
}

func bisectRight(items []int, value int) int {
	return sort.SearchInts(items, value+1)
}

func binarySearch(items []int, value int) int {
	index := bisectLeft(items, value)
	if index != len(items) && items[index] == value {
		return index
	}
	return -1
}

func containsNearbyAlmostDuplicate(nums []int, k int, t int) bool {
	var sortedSet []int
	var ordered []int
	for _, num := range nums {
		ceiling := bisectLeft(sortedSet, num)
		floor := bisectRight(sortedSet, num)
		ceilingOk := ceiling < len(sortedSet)
		ceilingOk = ceilingOk && sortedSet[ceiling]-num <= t
		floorOk := floor != 0 && floor-1 < len(sortedSet)
		floorOk = floorOk && num-sortedSet[floor-1] <= t
		if ceilingOk || floorOk {
			return true
		}
		ordered = append(ordered, num)
		if binarySearch(sortedSet, num) == -1 {
			sortedSet = slices.Insert(sortedSet, bisectRight(sortedSet, num), num)
		}
		if len(ordered) > k {
			index := binarySearch(sortedSet, ordered[0])
			ordered = ordered[1:]
			sortedSet = slices.Delete(sortedSet, index, index+1)
		}
	}
	return false
}
