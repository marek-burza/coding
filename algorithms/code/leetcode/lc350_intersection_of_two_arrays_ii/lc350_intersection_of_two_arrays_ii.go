// Package lc350 implements https://leetcode.com/problems/intersection-of-two-arrays-ii/
package lc350

import "sort"

func intersect(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	var found []int
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
		found = append(found, nums1[i1])
		i1++
		i2++
	}
	return found
}
