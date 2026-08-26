// Package lc349 implements https://leetcode.com/problems/intersection-of-two-arrays/
package lc349

import (
	"maps"
	"slices"
	"sort"
)

func intersection(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	found := make(map[int]struct{})
	i1 := 0
	i2 := 0
	for i1 < len(nums1) && i2 < len(nums2) {
		if nums1[i1] < nums2[i2] {
			i1++
			continue
		}
		if nums1[i1] > nums2[i2] {
			i2++
			continue
		}
		found[nums1[i1]] = struct{}{}
		i1++
		i2++
	}
	listed := slices.Collect(maps.Keys(found))
	return listed
}
